package technology.svelte.lexer;


import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.io.Reader;


public class SvelteLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            SvelteTokenTypes.WHITE_SPACE,
            SvelteTokenTypes.TEMPLATE_HTML_TEXT
    );

    public SvelteLexer() {
        super(new FlexAdapter(new technology.svelte.lexer._SvelteLexer((Reader) null)), TOKENS_TO_MERGE);
    }
}