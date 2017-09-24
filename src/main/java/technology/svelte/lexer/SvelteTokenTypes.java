package technology.svelte.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import technology.svelte.SvelteLanguage;

/**
 * @author Dietrich Schulten - ds@escalon.de
 */
public interface SvelteTokenTypes {
    // large grain parsing
    public static final IElementType TEMPLATE_HTML_TEXT =
            new SvelteElementType("SVELTE_TEMPLATE_HTML_TEXT"); // produced by lexer for all HTML code


    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

    IElementType OPENING = new SvelteElementType("OPENING");
    IElementType CLOSING = new SvelteElementType("CLOSING");

    IElementType OPEN_BLOCK = new SvelteElementType("OPEN-BLOCK");
    IElementType CLOSE_BLOCK = new SvelteElementType("CLOSE-BLOCK");


    IElementType SVELTE_PARAMS = new SvelteElementType("PARAMS");
    IElementType BLOCK_STMT_NAME = new SvelteElementType("BLOCK_STMT_NAME");
    IElementType MACRO = new SvelteElementType("MACRO");

    IFileElementType FILE = new IFileElementType("FILE", SvelteLanguage.SVELTE_LANGUAGE);

    public static final IElementType OUTER_ELEMENT_TYPE = new SvelteElementType("SVELTE_FRAGMENT");
    public static final TemplateDataElementType TEMPLATE_DATA =
            new TemplateDataElementType("SVELTE_TEMPLATE_DATA", SvelteLanguage.SVELTE_LANGUAGE, TEMPLATE_HTML_TEXT, OUTER_ELEMENT_TYPE);
//    IElementType PARAMS = new SvelteElementType("PARAMS");

//    IElementType TAG_START = new SvelteElementType("TAG-START");
//    IElementType TAG_CLOSING = new SvelteElementType("TAG-CLOSING");
//    IElementType END_TAG = new SvelteElementType("END_TAG");
//    IElementType TAG_NAME = new SvelteElementType("TAG-NAME");
//    IElementType OUTER_TEXT = new SvelteElementType("OUTER-TEST");
//    IElementType SVELTE_STRING = new SvelteElementType("SVELTE-STRING");
//
//    IElementType N_ATTR = new SvelteElementType("N-ATTR");
//    IElementType ATTR = new SvelteElementType("ATTR");
//    IElementType ATTR_VALUE = new SvelteElementType("ATTR-VALUE");




    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet STRING_LITERALS = TokenSet.create();
}
