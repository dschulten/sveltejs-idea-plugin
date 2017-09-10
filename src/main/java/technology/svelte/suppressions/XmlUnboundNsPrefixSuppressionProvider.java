package technology.svelte.suppressions;

import com.intellij.codeInspection.DefaultXmlSuppressionProvider;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class XmlUnboundNsPrefixSuppressionProvider extends DefaultXmlSuppressionProvider {

    @Override
    public boolean isProviderAvailable(@NotNull PsiFile file) {
        boolean ret;
        Language language = file.getLanguage();
        String displayName = language.getDisplayName();
        if ("HTML".equals(displayName)) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    @Override
    public boolean isSuppressedFor(@NotNull PsiElement element, @NotNull String inspectionId) {
        boolean ret;
        if ("XmlUnboundNsPrefix".equals(inspectionId)) {
            ret = true;
        } else {
            ret = super.isSuppressedFor(element, inspectionId);
        }
        return ret;
    }

}
