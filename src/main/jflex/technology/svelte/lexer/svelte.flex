package technology.svelte.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static technology.svelte.lexer.SvelteTokenTypes.*;

%%

%class _SvelteLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

STRING = \'(\\.|[^\'\\])*\'|\"(\\.|[^\"\\])*\"
CRLF = \n | \r | \r\n
WHITE_SPACE_CHAR = [\ \n\r\t\f]
OPENING = "{{"
CLOSING = "}}"
BLOCK_STMT_NAME = [^\'\"{} ]+


%state AFTER_LD
%state IN_SVELTE
%state IN_TAG
%state TAG_STARTED
%state ATTR_NAME
%state IN_ATTR_VALUE
%state IN_BLOCK_STMT

%%




<YYINITIAL> {
    {OPENING}                    { yybegin(AFTER_LD); return OPENING; }
//    "</"                         { yybegin(TAG_STARTED); return TEMPLATE_HTML_TEXT; /* TAG_CLOSING; */ }
//    "<" / [a-z0-9:]              { yybegin(TAG_STARTED); return TEMPLATE_HTML_TEXT; /* TAG_START; */ }
//    [^{<]+                       { return TEMPLATE_HTML_TEXT; }
//    [^{}]+                       { return TEMPLATE_HTML_TEXT; }
    {WHITE_SPACE_CHAR} | [\$\(\)\[\]&§?{}\\´`~#|\^°_*+\-%;,:\\./<!>=\"'] | [:letter:] | [:digit:]                    { return TEMPLATE_HTML_TEXT; }
}

<AFTER_LD> {
    "#"                           { yybegin(IN_BLOCK_STMT); return OPEN_BLOCK; }
    "else"                        { yybegin(IN_SVELTE); return BLOCK_STMT_NAME; }
    "elseif"                      { yybegin(IN_SVELTE); return BLOCK_STMT_NAME; }
    "/"                           { yybegin(IN_BLOCK_STMT); return CLOSE_BLOCK; }
    [^#]                          { yybegin(IN_SVELTE);  }
}

<IN_BLOCK_STMT> {
    {BLOCK_STMT_NAME}             { yybegin(IN_SVELTE); return BLOCK_STMT_NAME; }
    {CLOSING}                     { yybegin(YYINITIAL); return CLOSING; }
}

<IN_SVELTE> {
    [^\}]+                        { return SVELTE_PARAMS; }
    {CLOSING}                   { yybegin(YYINITIAL); return CLOSING; }
}

<TAG_STARTED> {
    [a-z0-9:]+                  { yybegin(IN_TAG); return TEMPLATE_HTML_TEXT; /* TAG_NAME; */ }
}

<IN_TAG> {
    [a-z0-9]+(={STRING})?       { return TEMPLATE_HTML_TEXT; }

    ">"                         { yybegin(YYINITIAL); return TEMPLATE_HTML_TEXT; /* END_TAG;*/ }
}

<ATTR_NAME> {
    "="                         { yybegin(IN_ATTR_VALUE); return TEMPLATE_HTML_TEXT; }
}

<IN_ATTR_VALUE> {
    {STRING}                    { yybegin(IN_TAG); return ATTR_VALUE; }
}



//{WHITE_SPACE_CHAR}+                      { return WHITE_SPACE; }
.                                        { return BAD_CHARACTER; }