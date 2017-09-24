package technology.svelte.lexer;

import com.intellij.psi.tree.IElementType;
import technology.svelte.SvelteLanguage;
import org.jetbrains.annotations.NotNull;


public class SvelteElementType extends IElementType {
    public SvelteElementType(@NotNull String debugName) {
        super(debugName, SvelteLanguage.SVELTE_LANGUAGE);
    }

    public String toString() {
        return "[Svelte] " + super.toString();
    }
}
