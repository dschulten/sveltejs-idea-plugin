package technology.svelte.psi.impl;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import technology.svelte.lexer.SvelteTokenTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Curly brackets macro
 */
public class MacroNodeImpl extends SveltePsiElement {

	public MacroNodeImpl(ASTNode node) {
		super(node);
	}

	public String getMacroName() {
		for(PsiElement el: getChildren()) {
			if(el.getNode().getElementType() == SvelteTokenTypes.BLOCK_STMT_NAME) return el.getText();
		}
		return null;
	}

	public PsiElement getParams() {
		for(PsiElement el: getChildren()) {
			if(el.getNode().getElementType() == SvelteTokenTypes.SVELTE_PARAMS) return el;
		}
		return null;
	}

}
