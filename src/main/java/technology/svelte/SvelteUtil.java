package technology.svelte;

import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.ecmascript6.psi.ES6FunctionProperty;
import com.intellij.lang.javascript.psi.JSProperty;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;

import java.util.*;

/**
 * Write Util to find data() return properties and methods in svelte methods (and computed etc.) using FileBasedIndex. Write SvelteJsReference with resolve methods that make use of it and define a SvelteJsReferenceContributor extension which produces SvelteJsReferences.
 *
 * @see <a href="https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/reference_contributor.html">Contributor</a>
 * @see <a href="https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/psi_helper_and_utilities.html">Util</a>
 */
public class SvelteUtil {
    public static List<JSProperty> findProperties(Project project, String key) {
        List<JSProperty> result = null;
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, HtmlFileType.INSTANCE,
                        GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile file = PsiManager.getInstance(project)
                    .findFile(virtualFile);
            if (file != null) {
                // drill down to script element with ES6FunctionProperty "data" and look at its properties
                Collection<JSProperty> properties = PsiTreeUtil.findChildrenOfAnyType(file, JSProperty.class);
                if (properties != null) {
                    for (JSProperty property : properties) {
                        if (key.equals(property.getName())) {
                            if (result == null) {
                                result = new ArrayList<JSProperty>();
                            }
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<JSProperty>emptyList();
    }

    public static List<JSProperty> findMethods(Project project, String key) {
        List<JSProperty> result = null;
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, HtmlFileType.INSTANCE,
                        GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile file = PsiManager.getInstance(project)
                    .findFile(virtualFile);
            if (file != null) {
                // drill down to script element with methods attribute
                JSProperty[] properties = PsiTreeUtil.getChildrenOfType(file, JSProperty.class);
                if (properties != null) {
                    for (JSProperty property : properties) {
                        if ("methods".equals(property.getName())) {
                            ES6FunctionProperty[] methods = PsiTreeUtil.getChildrenOfType(property, ES6FunctionProperty.class);
                            for (ES6FunctionProperty method : methods) {
                                if (key.equals(method.getName())) {
                            if (result == null) {
                                result = new ArrayList<JSProperty>();
                            }
                            result.add(method);

                                }
                            }
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<JSProperty>emptyList();
    }

//    public static List<SimpleProperty> findProperties(Project project) {
//        List<SimpleProperty> result = new ArrayList<SimpleProperty>();
//        Collection<VirtualFile> virtualFiles =
//                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SimpleFileType.INSTANCE,
//                        GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (simpleFile != null) {
//                SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
//                if (properties != null) {
//                    Collections.addAll(result, properties);
//                }
//            }
//        }
//        return result;
//    }
}