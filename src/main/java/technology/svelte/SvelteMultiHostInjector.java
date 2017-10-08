package technology.svelte;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiManagerEx;
import com.intellij.psi.impl.source.xml.XmlAttributeValueImpl;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.psi.templateLanguages.OuterLanguageElementImpl;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class SvelteMultiHostInjector implements MultiHostInjector {

    // TODO move this to createElement in SvelteParserDefinition
    public static class SvelteLanguageInjectionHost implements PsiElement, PsiLanguageInjectionHost {

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public Project getProject() throws PsiInvalidElementAccessException {
            return outerLanguageElement.getProject();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public Language getLanguage() {
            return outerLanguageElement.getLanguage();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiManagerEx getManager() {
            return (PsiManagerEx)outerLanguageElement.getManager();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public PsiElement[] getChildren() {
            return outerLanguageElement.getChildren();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getParent() {
            return outerLanguageElement;
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getFirstChild() {
            return outerLanguageElement.getFirstChild();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getLastChild() {
            return outerLanguageElement.getLastChild();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getNextSibling() {
            return outerLanguageElement.getNextSibling();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getPrevSibling() {
            return outerLanguageElement.getPrevSibling();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
            return outerLanguageElement.getContainingFile();
        }

        @Override
        @Contract(
            pure = true
        )
        public TextRange getTextRange() {
            return outerLanguageElement.getTextRange();
        }

        @Override
        @Contract(
            pure = true
        )
        public int getStartOffsetInParent() {
            return outerLanguageElement.getStartOffsetInParent();
        }

        @Override
        @Contract(
            pure = true
        )
        public int getTextLength() {
            return outerLanguageElement.getTextLength();
        }

        @Override
        @Nullable
        @Contract(
            pure = true
        )
        public PsiElement findElementAt(int offset) {
            return outerLanguageElement.findElementAt(offset);
        }

        @Override
        @Nullable
        @Contract(
            pure = true
        )
        public PsiReference findReferenceAt(int offset) {
            return outerLanguageElement.findReferenceAt(offset);
        }

        @Override
        @Contract(
            pure = true
        )
        public int getTextOffset() {
            return outerLanguageElement.getTextOffset();
        }

        @NotNull
        @Override
        @NonNls
        @Contract(
            pure = true
        )
        public String getText() {
            return outerLanguageElement.getText();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public char[] textToCharArray() {
            return outerLanguageElement.textToCharArray();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getNavigationElement() {
            return outerLanguageElement.getNavigationElement();
        }

        @Override
        @Contract(
            pure = true
        )
        public PsiElement getOriginalElement() {
            return outerLanguageElement.getOriginalElement();
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean textMatches(@NotNull CharSequence text) {
            return outerLanguageElement.textMatches(text);
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean textMatches(@NotNull PsiElement element) {
            return outerLanguageElement.textMatches(element);
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean textContains(char c) {
            return outerLanguageElement.textContains(c);
        }

        @Override
        public void accept(@NotNull PsiElementVisitor visitor) {
            outerLanguageElement.accept(visitor);
        }

        @Override
        public void acceptChildren(@NotNull PsiElementVisitor visitor) {
            outerLanguageElement.acceptChildren(visitor);
        }

        @Override
        public PsiElement copy() {
            return outerLanguageElement.copy();
        }

        @Override
        public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException {
            return outerLanguageElement.add(element);
        }

        @Override
        public PsiElement addBefore(@NotNull PsiElement element, @Nullable PsiElement anchor) throws IncorrectOperationException {
            return outerLanguageElement.addBefore(element, anchor);
        }

        @Override
        public PsiElement addAfter(@NotNull PsiElement element, @Nullable PsiElement anchor) throws IncorrectOperationException {
            return outerLanguageElement.addAfter(element, anchor);
        }

        @Override
        public void checkAdd(@NotNull PsiElement element) throws IncorrectOperationException {
            outerLanguageElement.checkAdd(element);
        }

        @Override
        public PsiElement addRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
            return outerLanguageElement.addRange(first, last);
        }

        @Override
        public PsiElement addRangeBefore(@NotNull PsiElement first, @NotNull PsiElement last, PsiElement anchor) throws IncorrectOperationException {
            return outerLanguageElement.addRangeBefore(first, last, anchor);
        }

        @Override
        public PsiElement addRangeAfter(PsiElement first, PsiElement last, PsiElement anchor) throws IncorrectOperationException {
            return outerLanguageElement.addRangeAfter(first, last, anchor);
        }

        @Override
        public void delete() throws IncorrectOperationException {
            outerLanguageElement.delete();
        }

        @Override
        public void checkDelete() throws IncorrectOperationException {
            outerLanguageElement.checkDelete();
        }

        @Override
        public void deleteChildRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
            outerLanguageElement.deleteChildRange(first, last);
        }

        @Override
        public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
            return outerLanguageElement.replace(newElement);
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean isValid() {
            return outerLanguageElement.isValid();
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean isWritable() {
            return outerLanguageElement.isWritable();
        }

        @Override
        @Nullable
        @Contract(
            pure = true
        )
        public PsiReference getReference() {
            return outerLanguageElement.getReference();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public PsiReference[] getReferences() {
            return outerLanguageElement.getReferences();
        }

        @Override
        @Nullable
        @Contract(
            pure = true
        )
        public <T> T getCopyableUserData(Key<T> key) {
            return outerLanguageElement.getCopyableUserData(key);
        }

        @Override
        public <T> void putCopyableUserData(Key<T> key, @Nullable T value) {
            outerLanguageElement.putCopyableUserData(key, value);
        }

        @Override
        public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
            return outerLanguageElement.processDeclarations(processor, state, lastParent, place);
        }

        @Override
        @Nullable
        @Contract(
            pure = true
        )
        public PsiElement getContext() {
            return outerLanguageElement.getContext();
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean isPhysical() {
            return outerLanguageElement.isPhysical();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public GlobalSearchScope getResolveScope() {
            return outerLanguageElement.getResolveScope();
        }

        @Override
        @NotNull
        @Contract(
            pure = true
        )
        public SearchScope getUseScope() {
            return outerLanguageElement.getUseScope();
        }

        @Override
        @Contract(
            pure = true
        )
        public ASTNode getNode() {
            return outerLanguageElement.getNode();
        }

        @Override
        @NonNls
        @Contract(
            pure = true
        )
        public String toString() {
            return outerLanguageElement.toString();
        }

        @Override
        @Contract(
            pure = true
        )
        public boolean isEquivalentTo(PsiElement another) {
            return outerLanguageElement.isEquivalentTo(another);
        }

        @Override
        @Nullable
        public <T> T getUserData(@NotNull Key<T> key) {
            return outerLanguageElement.getUserData(key);
        }

        @Override
        public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
            outerLanguageElement.putUserData(key, value);
        }

        @Nullable
        @Override
        public Icon getIcon(int flags) {
            return outerLanguageElement.getIcon(flags);
        }

        private PsiElement outerLanguageElement;

        public SvelteLanguageInjectionHost(OuterLanguageElementImpl outerLanguageElement) {
            //super(outerLanguageElement.getElementType(), outerLanguageElement.getText());
            this.outerLanguageElement = outerLanguageElement;
        }

        @Override
        public boolean isValidHost() {
            return true;
        }

        @Override
        public PsiLanguageInjectionHost updateText(@NotNull String text) {
            return this;
        }

        @NotNull
        @Override
        public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
            return LiteralTextEscaper.createSimple(this);
        }
    }

    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (context instanceof OuterLanguageElementImpl) {
            OuterLanguageElementImpl outerLanguageElement = (OuterLanguageElementImpl) context;
            String text = outerLanguageElement.getText();
//            outerLanguageElement.getValue();
            if (text.startsWith("{{#if")) {
                final Language language = ((LanguageFileType) FileTypeManager.getInstance()
                        .getFileTypeByExtension("js")).getLanguage();
                String prefix = "";
                String suffix = "";
                PsiLanguageInjectionHost psiLanguageInjectionHost = new SvelteLanguageInjectionHost(outerLanguageElement);

                registrar.startInjecting(language);
                registrar.addPlace(prefix, suffix, psiLanguageInjectionHost,
                        new TextRange(5, text.length() - 2));
                registrar.doneInjecting();
            }
        } else if (context instanceof XmlAttribute) {
            XmlAttribute xmlAttribute = (XmlAttribute) context;
            String name = xmlAttribute.getName();
            if (name.startsWith("on:")) {
                String value = xmlAttribute.getValue();
                XmlAttributeValue valueElement = xmlAttribute.getValueElement();
                final Language language = ((LanguageFileType) FileTypeManager.getInstance()
                        .getFileTypeByExtension("js")).getLanguage();
                String prefix = "";
                String suffix = ";";
//                PsiLanguageInjectionHost psiLanguageInjectionHost = new SvelteLanguageInjectionHost(outerLanguageElement);

                registrar.startInjecting(language);
                registrar.addPlace(prefix, suffix, (XmlAttributeValueImpl)valueElement,
                        new TextRange(1, value.length() + 1));
                registrar.doneInjecting();

            } else if (name.startsWith("ref:")) {

            } else if (name.startsWith("bind:")) {

            }
        }

    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Arrays.asList(OuterLanguageElementImpl.class, XmlAttribute.class);
    }
}
