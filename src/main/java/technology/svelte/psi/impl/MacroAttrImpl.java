package technology.svelte.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import technology.svelte.lexer.SvelteTokenTypes;

/**
 * Curly brackets macro
 */
public class MacroAttrImpl extends SveltePsiElement {

	public MacroAttrImpl(ASTNode node) {
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
