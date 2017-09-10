package technology.svelte.lang;

import com.intellij.lang.html.HTMLLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SvelteFileType extends LanguageFileType {

    public static final SvelteFileType INSTANCE = new SvelteFileType();

    private SvelteFileType() {
        super(HTMLLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Svelte component";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Svelte component file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "svelte";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SvelteIcons.FILE;
    }
}