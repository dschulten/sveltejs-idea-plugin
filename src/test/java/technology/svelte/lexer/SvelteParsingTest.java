package technology.svelte.lexer;

import com.intellij.lang.LanguageASTFactory;
import com.intellij.openapi.application.PluginPathManager;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.PlatformTestCase;
import technology.svelte.parser.SvelteParserDefinition;

public class SvelteParsingTest extends ParsingTestCase {
    public SvelteParsingTest() {
        super("", "svelte", new SvelteParserDefinition());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //addExplicitExtension(LanguageASTFactory.INSTANCE, SvelteLanguage.Svelte_LANGUAGE, );
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/data";
    }
    
    public void testSample1() throws Exception { doTest(true); }
}
