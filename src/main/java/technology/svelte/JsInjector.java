package technology.svelte;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class JsInjector implements LanguageInjector {

    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host,
                                     @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
        if (host instanceof PsiComment && ((PsiComment) host).getTokenType() ==
                JavaTokenType.C_STYLE_COMMENT) {
            PsiComment comment = (PsiComment) host;
            String text = comment.getText();

            if (!text.startsWith("/-{") || !text.endsWith("}-/")) return;

            final PsiElement parent = host.getParent();
            if (parent != null && parent instanceof PsiMethod) {
                PsiMethod method = (PsiMethod) parent;
                if (method.getModifierList()
                        .hasExplicitModifier("native")) {
                    @NonNls StringBuilder prefix = new StringBuilder();
                    prefix.append("function ");
                    prefix.append(method.getName());
                    prefix.append(" ( ");
                    final PsiParameter[] parameters = method.getParameterList()
                            .getParameters();
                    for (int i = 0; i != parameters.length; ++i) {
                        prefix.append("/*");
                        prefix.append(parameters[i].getType()
                                .toString());
                        prefix.append("*/");
                        prefix.append(parameters[i].getName());
                        prefix.append(",");
                    }

                    prefix.append("/*");
                    prefix.append("window");
                    prefix.append("*/");
                    prefix.append("$wnd");
                    prefix.append(",");

                    prefix.append("/*");
                    prefix.append("Document");
                    prefix.append("*/");
                    prefix.append("$doc");

                    prefix.append(") {");

                    final Language language = ((LanguageFileType) FileTypeManager.getInstance()
                            .getFileTypeByExtension("js")).getLanguage();
                    String suffix = "}";
                    injectionPlacesRegistrar.addPlace(language, new TextRange(4, text.length()
                            - 4), prefix.toString(), suffix);
                }
            }
        }
    }
}