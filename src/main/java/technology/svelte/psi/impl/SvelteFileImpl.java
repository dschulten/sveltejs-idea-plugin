package technology.svelte.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import technology.svelte.SvelteLanguage;
import technology.svelte.file.SvelteFileType;
import technology.svelte.psi.SvelteFile;
import org.jetbrains.annotations.NotNull;

public class SvelteFileImpl extends PsiFileBase implements SvelteFile {
    public SvelteFileImpl(FileViewProvider viewProvider) {
        super(viewProvider, SvelteLanguage.SVELTE_LANGUAGE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SvelteFileType.SVELTE_FILE_TYPE;
    }

    @Override
    public String toString() {
        return "SvelteFile:" + getName();
    }
}
