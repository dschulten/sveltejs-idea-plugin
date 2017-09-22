package technology.svelte.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import technology.svelte.lexer.SvelteTokenTypes;
import org.jetbrains.annotations.NotNull;

public class SvelteParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        Marker marker = builder.mark();
        
        // Process all tokens
        while(!builder.eof()) {
            IElementType type = builder.getTokenType();

            if(type == SvelteTokenTypes.OPENING) parseMacro(builder);
            else if(type == SvelteTokenTypes.N_ATTR) parseNAttr(builder);

            builder.advanceLexer(); // move to next token
        }
        
        marker.done(root);
        return builder.getTreeBuilt();
    }

    // {macro ...}
    private void parseMacro(PsiBuilder builder) {
        Marker macroStart = builder.mark();
        builder.advanceLexer();

        // is there a name?
        String tagName = null;
        if(builder.getTokenType() == SvelteTokenTypes.MACRO_NAME) {
            Marker macroNameMark = builder.mark();
            tagName = builder.getTokenText();
            builder.advanceLexer();
            macroNameMark.done(SvelteTokenTypes.MACRO_NAME);
        }

        // params
        Marker paramsMark = builder.mark();
        parseParams(tagName, builder, SvelteTokenTypes.CLOSING);
        paramsMark.done(SvelteTokenTypes.PARAMS);

        // finish him
        if(builder.getTokenType() == SvelteTokenTypes.CLOSING) {
            builder.advanceLexer();
        }
        macroStart.done(SvelteTokenTypes.MACRO_NODE);
    }

    // n:link="something"
    // n:link=something
    private void parseNAttr(PsiBuilder builder) {
        Marker start = builder.mark();
        builder.advanceLexer();
        
        // Process name
        String attrName = null;
        if(builder.getTokenType() == SvelteTokenTypes.ATTR_NAME) {
            Marker macroName =  builder.mark();
            attrName = "@" + builder.getTokenText();
            builder.advanceLexer();
            macroName.done(SvelteTokenTypes.MACRO_NAME);
        }
        
        if(builder.getTokenType() == SvelteTokenTypes.N_ATTR_EQ) builder.advanceLexer();
        
        boolean inQuotes;
        if(builder.getTokenType() == SvelteTokenTypes.N_QUOTE) {
            inQuotes = true;
            builder.advanceLexer();
        } else inQuotes = false;


        // Process value
        Marker value = builder.mark();
        parseParams(attrName, builder, inQuotes ? SvelteTokenTypes.N_QUOTE : SvelteTokenTypes.TEMPLATE_HTML_TEXT);
        value.done(SvelteTokenTypes.PARAMS);

        if(inQuotes && builder.getTokenType() == SvelteTokenTypes.N_QUOTE) {
            builder.advanceLexer();
        }

        start.done(SvelteTokenTypes.MACRO_ATTR);
    }

    // custom params
    private void parseParams(String macroName, PsiBuilder builder, IElementType closing) {
        // just process it atm
        while(builder.getTokenType() != closing && !builder.eof()) {
            builder.advanceLexer();
        }
        
    }
}
