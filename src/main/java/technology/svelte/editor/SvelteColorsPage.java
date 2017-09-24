package technology.svelte.editor;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import technology.svelte.SvelteIcons;

import javax.swing.*;
import java.util.Map;


/**
 * Settings dialog for colors
 */
public class SvelteColorsPage implements ColorSettingsPage {
    public static final AttributesDescriptor[] ATTRS = {
            new AttributesDescriptor("colors.bad.character", SvelteSyntaxHighlighter.BAD_CHARACTER),
//            new AttributesDescriptor("colors.comment",       SvelteSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("colors.brackets",      SvelteSyntaxHighlighter.TEMPLATE),
//            new AttributesDescriptor("colors.identifier",    SvelteSyntaxHighlighter.IDENTIFIER),
//            new AttributesDescriptor("colors.attribute",     SvelteSyntaxHighlighter.ATTR),
//            new AttributesDescriptor("colors.keyword",       SvelteSyntaxHighlighter.KEYWORD),
//            new AttributesDescriptor("colors.variable",      SvelteSyntaxHighlighter.VARIABLE),
//            new AttributesDescriptor("colors.string",        SvelteSyntaxHighlighter.STRING),
//            new AttributesDescriptor("colors.punctuation",   SvelteSyntaxHighlighter.PUNCTUATION),
    };

    @NotNull
    @Override
    public String getDisplayName() {
        return "Svelte";
    }

    @Override
    public Icon getIcon() {
        return SvelteIcons.FILETYPE_ICON;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return ATTRS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SvelteSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "<div class='widget-container'>\n" +
                "    <Widget foo bar='static' :dynamic/>\n" +
                "    <button on:click=\"hello()\">Say somepin</button>\n" +
                "    {{#if alive}}\n" +
                "    <div>Cat is alive</div>\n" +
                "    {{elseif alive === undefined}}\n" +
                "    Cat is both alive and not alive\n" +
                "    {{else}}\n" +
                "    Cat is dead\n" +
                "    {{/if}}\n" +
                "</div>";
    }

    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }
}
