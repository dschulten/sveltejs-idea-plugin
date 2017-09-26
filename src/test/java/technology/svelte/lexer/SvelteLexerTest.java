package technology.svelte.lexer;


import com.intellij.openapi.util.io.FileUtil;

import java.io.File;

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

    public void testTriple() throws Exception {
        LexerToken[] tokens = lex("Renders as {{{html}}}");

        assertToken(tokens[0], SvelteTokenTypes.TEMPLATE_HTML_TEXT, "Renders as ");
        assertToken(tokens[1], SvelteTokenTypes.OPENING, "{{{");
        assertToken(tokens[2], SvelteTokenTypes.SVELTE_PARAMS, "html");
        assertToken(tokens[3], SvelteTokenTypes.CLOSING, "}}}");
    }

    public void testPlainHtmlIsOneToken() throws Exception {
        String testPath = getTestDataPath() + "/plain.html";
        String data = FileUtil.loadFile(new File(testPath));

        LexerToken[] tokens = lex(data);
        assertEquals(1, tokens.length);
    }

    public void testPlainHtmlLineByLine() throws Exception {
        String[] htmlSnippets = new String[] {
                "<!DOCTYPE html>",
                "\n\n<html><head><body>",
                "<script type=\"text/javascript\" src=\"/js/cms.js\"></script>",
                "<!-- Load TinyMCE -->",
                "<div id=\"layout_header\">",
                "\t<script type=\"text/javascript\">\n" +
                        "\t\tvar x = {};\n" +
                        "\t\tvar y = { a: 1 };\n" +
                        "\t</script>"
        };

        for(String snippet: htmlSnippets) {
            LexerToken[] tokens = lex(snippet);
            assertEquals("HTML snippet lexed as more than one token: " + snippet, 1, tokens.length);
        }
    }
}
