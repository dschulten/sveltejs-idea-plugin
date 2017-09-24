package technology.svelte.lexer;


import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.io.Reader;


public class SvelteTopLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            SvelteTokenTypes.WHITE_SPACE,
            SvelteTokenTypes.TEMPLATE_HTML_TEXT
    );

    public SvelteTopLexer() {
        super(null, TOKENS_TO_MERGE);
//       super(new LookAheadLexer(new FlexAdapter(new _SvelteTopLexer((Reader) null))) {
//
//            @Override
//            protected void lookAhead(Lexer lex) {
//                IElementType type = lex.getTokenType();
//                if(type == SvelteTokenTypes.N_ATTR) { // n: attr - keep all interesting tokens as they are
//                    addToken(type);
//                    lex.advance();
//
//                    while (SvelteTokenTypes.nAttrSet.contains(type = lex.getTokenType())) {
//                        addToken(type);
//                        lex.advance();
//                    }
//                }
//
//                if(SvelteTokenTypes.nAttrSet.contains(type)) addToken(SvelteTokenTypes.TEMPLATE_HTML_TEXT); // otherwise replace attributes by generic HTML
//                else addToken(type);
//
//                lex.advance();
//            }
//        }, TOKENS_TO_MERGE);
    }
}