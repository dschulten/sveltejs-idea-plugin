package technology.svelte.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

import java.io.Reader;


public class SvelteSubLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            SvelteTokenTypes.WHITE_SPACE
    );

    public SvelteSubLexer() {
        super(null, TOKENS_TO_MERGE);
//        super(new FlexAdapter(new _SvelteSubLexer((Reader) null)), TOKENS_TO_MERGE);
    }
}
