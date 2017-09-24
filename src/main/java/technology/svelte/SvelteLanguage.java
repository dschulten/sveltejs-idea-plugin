package technology.svelte;


import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import technology.svelte.editor.SvelteSyntaxHighlighter;
import technology.svelte.editor.SvelteTemplateHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SvelteLanguage extends Language implements TemplateLanguage {
    // singleton
    public static final SvelteLanguage SVELTE_LANGUAGE = new SvelteLanguage();

    public SvelteLanguage() {
        super("Svelte", "application/x-svelte-template");
    }
}
