package technology.svelte.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * Uses base-lexer to get big chunks, and several of them (typesToLex) are then lexed into smaller tokens using sub-lexer.
 *
 * Useful in some templating languages, where you lex-out whole chunks of your language, which is then lexed by another lexer.
 *
 * @author Jan Dolecek <juzna.cz@gmail.com>
 */
public class LayeredLexer extends LookAheadLexer {
    protected Lexer subLexer;
    protected TokenSet typesToLex;

    /**
     * @param baseLexer  Lexer which creates big chunks
     * @param subLexer   Lexer which is applied to chunks from previous lexer
     * @param typesToLex Only these chunks from base-lexer are processed
     */
    public LayeredLexer(Lexer baseLexer, Lexer subLexer, TokenSet typesToLex) {
        super(baseLexer);
        this.subLexer = subLexer;
        this.typesToLex = typesToLex;
    }

    @Override
    protected void lookAhead(Lexer baseLexer) {
        if(typesToLex.contains(baseLexer.getTokenType())) {
            // Parse all sub tokens
            IElementType subToken;
            subLexer.start(baseLexer.getBufferSequence(), baseLexer.getTokenStart(), baseLexer.getTokenEnd());
            while((subToken = subLexer.getTokenType()) != null) {
                addToken(subLexer.getTokenEnd(), subToken);
                subLexer.advance();
            }

            baseLexer.advance();

        } else {
            addToken(baseLexer.getTokenType());
            baseLexer.advance();
        }
    }
}
