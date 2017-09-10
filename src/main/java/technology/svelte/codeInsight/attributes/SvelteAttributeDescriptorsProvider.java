package technology.svelte.codeInsight.attributes;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlAttributeDescriptorsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SvelteAttributeDescriptorsProvider implements XmlAttributeDescriptorsProvider {

    private final List<String> svelteDirectives = Arrays.asList("on:", "bind:", "ref:");

    @Override
    public XmlAttributeDescriptor[] getAttributeDescriptors(XmlTag context) {
        final Map<String, XmlAttributeDescriptor> result = new LinkedHashMap<>();
        XmlAttribute[] attributes = context.getAttributes();
        for (XmlAttribute attribute : attributes) {
            String attributeName = attribute.getName();
            if(attributeName
                    .contains(":")) {
                result.put(attributeName, new SvelteAttributeDescriptor(context.getProject(), attributeName));
            }
        }
        return result.values()
                .toArray(new XmlAttributeDescriptor[result.size()]);
    }

    @Nullable
    @Override
    public XmlAttributeDescriptor getAttributeDescriptor(String attributeName, XmlTag context) {
        XmlAttributeDescriptor ret;
        if (attributeName.contains(":")) {
            ret = new SvelteAttributeDescriptor(context.getProject(), attributeName);
        } else {
            ret = null;
        }
        return ret;
    }
}
