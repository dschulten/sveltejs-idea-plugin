package technology.svelte;

import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;
import technology.svelte.lang.SvelteFileType;

public class SvelteFileFactory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(HtmlFileType.INSTANCE);
    }
}
