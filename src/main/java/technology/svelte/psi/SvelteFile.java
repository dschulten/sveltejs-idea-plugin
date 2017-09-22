package technology.svelte.psi;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.impl.PsiFileEx;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: juzna
 * Date: 1/26/12
 * Time: 1:35 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SvelteFile extends PsiFileEx {
	@NotNull
	@Override
	FileType getFileType();
}
