package technology.svelte.lexer;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.application.PluginPathManager;
import com.intellij.openapi.application.ex.PathManagerEx;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.PlatformLiteFixture;

import java.util.ArrayList;


public abstract class AbstractLexerTest extends PlatformLiteFixture {
    Lexer lexer;

	protected String getTestDataPath() {
		return "src/test/resources/data";
	}

    protected void assertToken(LexerToken token, IElementType type, String content) {
        if(type != null) assertEquals(type, token.type);
        if(content != null) assertEquals(content, token.content);
    }

    protected LexerToken[] lex(String str) {
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();
        IElementType el;

        lexer.start(str);

        while((el = lexer.getTokenType()) != null) {
            tokens.add(new LexerToken(el, lexer.getTokenStart(), lexer.getTokenEnd(), lexer.getTokenText()));
            lexer.advance();
        }

        return tokens.toArray(new LexerToken[tokens.size()]);
    }
}
