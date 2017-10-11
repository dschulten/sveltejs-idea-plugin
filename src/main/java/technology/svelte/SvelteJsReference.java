package technology.svelte;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.javascript.psi.JSProperty;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import icons.JavaScriptLanguageIcons;
import icons.JavaScriptPsiIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SvelteJsReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private String key;

    public SvelteJsReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    /**
     * Returns the element which is the target of the reference.
     *
     * @return the target element, or null if it was not possible to resolve the reference to a valid target.
     * @see PsiPolyVariantReference#multiResolve(boolean)
     */
    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    /**
     * Returns the array of String, {@link PsiElement} and/or {@link LookupElement}
     * instances representing all identifiers that are visible at the location of the reference. The contents
     * of the returned array is used to build the lookup list for basic code completion. (The list
     * of visible identifiers may not be filtered by the completion prefix string - the
     * filtering is performed later by IDEA core.)
     *
     * @return the array of available identifiers.
     */
    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<JSProperty> properties = SvelteUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final JSProperty property : properties) {
            if (property.getName() != null && property.getName().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(JavaScriptPsiIcons.Classes.JavaScriptClass).
                        withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

    /**
     * Returns the results of resolving the reference.
     *
     * @param incompleteCode if true, the code in the context of which the reference is
     *                       being resolved is considered incomplete, and the method may return additional
     *                       invalid results.
     * @return the array of results for resolving the reference.
     */
    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<JSProperty> properties = SvelteUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (JSProperty property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }
}
