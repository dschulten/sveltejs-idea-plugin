package technology.svelte.lexer;


public class SvelteLexerTest extends AbstractLexerTest {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        lexer = new SvelteLexer();
    }

    public void testIfElseEndif() throws Exception {
        LexerToken[] tokens = lex("Now you {{#if visible}}see me{{else}}don't{{/if}}");

        assertToken(tokens[0], SvelteTokenTypes.TEMPLATE_HTML_TEXT, "Now you ");
        assertToken(tokens[1], SvelteTokenTypes.OPENING, "{{");
        assertToken(tokens[2], SvelteTokenTypes.OPEN_BLOCK, "#");
        assertToken(tokens[3], SvelteTokenTypes.BLOCK_STMT_NAME, "if");
        assertToken(tokens[4], SvelteTokenTypes.SVELTE_PARAMS, " visible");
        assertToken(tokens[5], SvelteTokenTypes.CLOSING, "}}");
        assertToken(tokens[6], SvelteTokenTypes.TEMPLATE_HTML_TEXT, "see me");
        assertToken(tokens[7], SvelteTokenTypes.OPENING, "{{");
        assertToken(tokens[8], SvelteTokenTypes.BLOCK_STMT_NAME, "else");
        assertToken(tokens[9], SvelteTokenTypes.CLOSING, "}}");
        assertToken(tokens[10], SvelteTokenTypes.TEMPLATE_HTML_TEXT, "don't");
        assertToken(tokens[11], SvelteTokenTypes.OPENING, "{{");
        assertToken(tokens[12], SvelteTokenTypes.CLOSE_BLOCK, "/");
        assertToken(tokens[13], SvelteTokenTypes.BLOCK_STMT_NAME, "if");
        assertToken(tokens[14], SvelteTokenTypes.CLOSING, "}}");
        assertEquals(15, tokens.length);
    }
}
