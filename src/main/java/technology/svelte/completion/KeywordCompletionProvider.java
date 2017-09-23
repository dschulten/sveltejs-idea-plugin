package technology.svelte.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlElement;
import com.intellij.util.ProcessingContext;
import technology.svelte.lexer.SvelteTokenTypes;
import technology.svelte.psi.impl.MacroNodeImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Provides keywords to be auto-completed
 */
public class KeywordCompletionProvider extends CompletionProvider<CompletionParameters> {
	// constant lists
	private static final String[] KEYWORDS = { "for", "foreach", "/for", "if", "else", "/if", "link", "plink" };
	private static final String[] FILTERS = { "date", "format", "replace", "url_encode", "json_encode", "title", "capitalize", "upper", "lower", "striptags", "join", "reverse", "length", "sort", "default", "keys", "escape", "raw", "merge" };

	// CompletionResultSet wants list of LookupElements
	private List<LookupElementBuilder> KEYWORD_LOOKUPS = new ArrayList();
	private List<LookupElementBuilder> FILTER_LOOKUPS = new ArrayList();


	public KeywordCompletionProvider() {
		super();

		for(String keyword: KEYWORDS) KEYWORD_LOOKUPS.add(LookupElementBuilder.create(keyword));
		for(String filter: FILTERS) FILTER_LOOKUPS.add(LookupElementBuilder.create(filter));
	}

	@Override
	protected void addCompletions(@NotNull CompletionParameters params,
								  ProcessingContext ctx,
								  @NotNull CompletionResultSet results) {

		PsiElement currElement = params.getPosition().getOriginalElement();
		if(currElement.getNode().getElementType() == SvelteTokenTypes.BLOCK_STMT_NAME) {
			for(LookupElementBuilder x: KEYWORD_LOOKUPS) results.addElement(x);
		}
	}
//    // constant lists
//    private static final String[] KEYWORDS = {
//		    // CoreMacros.php
//		    "if", "ifset", "for", "foreach", "while", "first", "last", "sep", "class", "attr", "captute",
//		    "var", "default", "dump", "debugbreak", "l", "r",
//
//		    // FormMacros.php
//		    "form", "input", "label", "formContainer",
//
//		    // UIMacros.php
//		    "include", "extends", "block", "define", "snippet", "widget", "control", "href", "link", "plink", "contentType", "status",
//    };
//
//	private static final List<String> PAIR_MACROS = Arrays.asList(
//			"if", "ifset", "for", "foreach", "while", "first", "last", "sep"
//	);
//
//	private static final String[] FILTERS = {
//			// static
//			"normalize", "toascii", "webalize", "truncate", "lower", "upper", "firstupper", "capitalize", "trim", "padleft", "padright",
//			"replacere", "url", "striptags", "nl2br", "substr", "repeat", "implode", "number",
//
//			// methods in Helpers.php
//			"espaceHtml", "escapeHtmlComment", "escapeXML", "escapeCss", "escapeJs", "strip", "indent", "date", "bytes",
//			"length", "replace", "dataStream", "null",
//	};
//
//
//	// CompletionResultSet wants list of LookupElements
//    private List<LookupElementBuilder> KEYWORD_LOOKUPS = new ArrayList();
//    private List<LookupElementBuilder> ATTR_LOOKUPS = new ArrayList();
//    private List<LookupElementBuilder> FILTER_LOOKUPS = new ArrayList();
//	private HashMap<String, LookupElementBuilder> CLOSING_LOOKUPS = new HashMap<String, LookupElementBuilder>();
//
//    public KeywordCompletionProvider() {
//        super();
//
//        for(String keyword: KEYWORDS) {
//	        KEYWORD_LOOKUPS.add(LookupElementBuilder.create(keyword));
//	        ATTR_LOOKUPS.add(LookupElementBuilder.create("n:" + keyword));
//        }
//        for(String filter: FILTERS) FILTER_LOOKUPS.add(LookupElementBuilder.create(filter));
//    }
//
//    @Override
//    protected void addCompletions(@NotNull CompletionParameters params,
//                                  ProcessingContext ctx,
//                                  @NotNull CompletionResultSet results) {
//
//	    PsiElement curr = params.getPosition().getOriginalElement();
//
//	    // n: attributes
//	    if(curr.getParent() instanceof XmlAttribute) {
//		    for(LookupElementBuilder x: ATTR_LOOKUPS) results.addElement(x);
//		    return;
//	    }
//
//	    // Keywords
//        if(curr.getNode().getElementType() == SvelteTokenTypes.MACRO_NAME) {
//            for(LookupElementBuilder x: KEYWORD_LOOKUPS) results.addElement(x);
//
//	        HashSet<String> openedMacros = new HashSet<String>(); // macros which are opened before (should complete closing)
//	        HashSet<String> closedMacros = new HashSet<String>(); // which are closed (should not complete closing)
//
//	        // Go up and find open keywords (and offer them for closing)
//	        PsiElement cursor = curr;
//	        while(cursor != null && (cursor.getPrevSibling() != null || cursor.getParent() != null)) {
//		        // macro found {xx} found
//		        if(cursor instanceof MacroNodeImpl) {
//			        MacroNodeImpl node = (MacroNodeImpl) cursor;
//			        String name = node.getMacroName();
//
//			        if(name.charAt(0) == '/') { // closing macro
//				        closedMacros.add(name.substring(1));
//			        } else if(PAIR_MACROS.contains(name)) {
//				        if(closedMacros.contains(name)) closedMacros.remove(name); // is closed later
//				        else openedMacros.add(name); // not closed, let us close it
//			        }
//		        }
//
//
//		        PsiElement tmp;
//		        if((tmp = cursor.getPrevSibling()) != null) cursor = tmp; // Go prev if possible...
//		        else cursor =  cursor.getParent(); // ... or up
//	        }
//
//	        for(String name: openedMacros) {
//		        if(name.equals("if")) {
//			        results.addElement(reuseClosingTag("else"));
//			        results.addElement(reuseClosingTag("elseif"));
//		        }
//		        results.addElement(reuseClosingTag("/" + name));
//	        }
//
//	        results.stopHere();
//        }
//
//	    // Modifiers (if after pipe)
//	    PsiElement prev = curr.getPrevSibling();
//	    if(prev != null && prev instanceof PsiWhiteSpace) prev = prev.getPrevSibling();
//	    if(prev != null && prev.getNode().getElementType() == SvelteTokenTypes.MODIFIER) {
//		    for(LookupElementBuilder x: FILTER_LOOKUPS) results.addElement(x);
//		    results.stopHere();
//	    }
//    }
//
//	// Get closing tag
//	private LookupElementBuilder reuseClosingTag(String name) {
//		if(!CLOSING_LOOKUPS.containsKey(name)) {
//			CLOSING_LOOKUPS.put(name, LookupElementBuilder.create(name));
//		}
//
//		return CLOSING_LOOKUPS.get(name);
//	}
}
