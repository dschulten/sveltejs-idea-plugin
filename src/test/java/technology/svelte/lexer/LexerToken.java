package technology.svelte.lexer;

import com.intellij.psi.tree.IElementType;

public class LexerToken {
    IElementType type;
    int start, end;
    String content;

    public LexerToken(IElementType type, int start, int end, String content) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.content = content;
    }

}