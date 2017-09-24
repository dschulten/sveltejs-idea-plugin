package technology.svelte.editor;


import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import technology.svelte.lexer.SvelteLexer;
import technology.svelte.lexer.SvelteTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class SvelteSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final String BAD_CHARACTER_ID = "Bad character";
    public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER.getDefaultAttributes()
            .clone());

    public static final String TEMPLATE_ID = "Template";
    public static final TextAttributesKey TEMPLATE = TextAttributesKey.createTextAttributesKey(TEMPLATE_ID, DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR);


    // Groups of IElementType's
    public static final TokenSet sBAD = TokenSet.create(SvelteTokenTypes.BAD_CHARACTER);
    public static final TokenSet sTEMPLATE = TokenSet.create(
            SvelteTokenTypes.OPENING,
            SvelteTokenTypes.CLOSING,
            SvelteTokenTypes.BLOCK_STMT_NAME,
            SvelteTokenTypes.OPEN_BLOCK,
            SvelteTokenTypes.CLOSE_BLOCK);

    // Static container
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();


    // Fill in the map
    static {
        fillMap(ATTRIBUTES, sBAD, BAD_CHARACTER);
        fillMap(ATTRIBUTES, sTEMPLATE, TEMPLATE);
    }


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SvelteLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        TextAttributesKey[] x = pack(ATTRIBUTES.get(type));
        return x;
    }
}
