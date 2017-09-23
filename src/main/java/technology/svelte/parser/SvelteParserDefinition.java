package technology.svelte.parser;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import technology.svelte.lexer.SvelteLexer;
import technology.svelte.lexer.SvelteTokenTypes;
import technology.svelte.psi.SvelteFile;
import technology.svelte.psi.impl.SvelteFileImpl;
import technology.svelte.psi.impl.SveltePsiElement;
import technology.svelte.psi.impl.MacroAttrImpl;
import technology.svelte.psi.impl.MacroNodeImpl;
import org.jetbrains.annotations.NotNull;

public class SvelteParserDefinition implements ParserDefinition {

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SvelteLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new SvelteParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return SvelteTokenTypes.FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return SvelteTokenTypes.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return SvelteTokenTypes.STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode astNode) {
        return new ASTWrapperPsiElement(astNode);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new SvelteFileImpl(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return SpaceRequirements.MAY;
    }

//    @NotNull
//    @Override
//    public Lexer createLexer(Project project) {
//        return new SvelteLexer();
//    }
//
//    @Override
//    public PsiParser createParser(Project project) {
//        return new SvelteParser();
//    }
//
//    @Override
//    public IFileElementType getFileNodeType() {
//        return SvelteTokenTypes.FILE;
//    }
//
//    @NotNull
//    @Override
//    public TokenSet getWhitespaceTokens() {
//        return SvelteTokenTypes.WHITESPACES;
//    }
//
//    @NotNull
//    @Override
//    public TokenSet getCommentTokens() {
//        return SvelteTokenTypes.COMMENTS;
//    }
//
//    @NotNull
//    @Override
//    public TokenSet getStringLiteralElements() {
//        return SvelteTokenTypes.STRING_LITERALS;
//    }
//
//    @NotNull
//    @Override
//    public PsiElement createElement(ASTNode node) {
//	    IElementType type = node.getElementType();
//
//	    if(type == SvelteTokenTypes.MACRO_NODE) return new MacroNodeImpl(node);
//	    else if(type == SvelteTokenTypes.MACRO_ATTR) return new MacroAttrImpl(node);
//	    else return new SveltePsiElement(node);
//    }
//
//    @Override
//    public PsiFile createFile(FileViewProvider fileViewProvider) {
//        return new SvelteFileImpl(fileViewProvider);
//    }
//
//    @Override
//    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
//        return SpaceRequirements.MAY;
//    }
}
