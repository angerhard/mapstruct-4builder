package de.andreasgerhard.mapstruct;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuilderAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

    private static final List<String> SETTERS = Arrays.asList(new String[]{"set", "is", "with"});
    private static final List<String> GETTERS = Arrays.asList(new String[]{"get"});
    private static final List<String> STOPLIST = Arrays.asList(new String[]{"hashCode", "toString", "equals"});

    private static final Pattern PATTERN_PREFIX = Pattern.compile("^(?<prefix>[a-z]+)\\w+");


    /**
     * Helper method, returns the prefix like get, set, with from method.
     *
     * @param name potential method name
     * @return all lower letter from beginning
     */
    private String getPrefix(String name) {
        String result = null;
        Matcher matcher = PATTERN_PREFIX.matcher(name);
        if (matcher.matches()) {
            result = matcher.group("prefix");
        }
        return result == null ? "" : result;
    }

    /**
     * Determines the class from the method and recognizes that it is a builder by class name or annotation.
     *
     * @param method the Method the class has to be determined.
     * @return true, when the class seems to be a builder.
     */
    private boolean classIsEnlistedAsBuilder(ExecutableElement method) {
        Element enclosingElement = method.getEnclosingElement();
        IsSetterWithoutPrefix annotation = enclosingElement.getAnnotation(IsSetterWithoutPrefix.class);
        boolean builder = enclosingElement.getSimpleName().toString().endsWith("Builder");
        return builder || annotation != null;
    }


    /**
     * @see DefaultAccessorNamingStrategy#isGetterMethod(ExecutableElement)
     */
    @Override
    public boolean isGetterMethod(ExecutableElement method) {
        boolean result = false;
        String methodName = method.getSimpleName().toString();
        String prefix = getPrefix(methodName);
        if (GETTERS.contains(prefix) && method.getReturnType().getKind() != TypeKind.VOID) {
            result = true;
        }
        return result;
    }

    /**
     * @see DefaultAccessorNamingStrategy#isSetterMethod(ExecutableElement)
     */
    @Override
    public boolean isSetterMethod(ExecutableElement method) {
        boolean result = false;
        String methodName = method.getSimpleName().toString();
        String prefix = getPrefix(methodName);
        TypeKind kind = method.getReturnType().getKind();
        if (    // annotated as Builder or ends with "Builder"
                classIsEnlistedAsBuilder(method)
                        // no method like hashCode, equals etc.
                        && !STOPLIST.contains(methodName)
                        // builder has a declared return type ...
                        && kind == TypeKind.DECLARED
                        // ... and declares himself as return type
                        && method.getReturnType().toString().equals(method.getEnclosingElement().toString())
                        // only one parameter
                        && method.getParameters().size() == 1) {
            result = true;
        } else if (SETTERS.contains(prefix)
                && kind == TypeKind.VOID
                && method.getParameters().size() == 1) {
            result = true;
        }
        return result;
    }

    /**
     * @see DefaultAccessorNamingStrategy#getPropertyName(ExecutableElement)
     */
    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod) {
        String methodName = getterOrSetterMethod.getSimpleName().toString();
        String prefix = getPrefix(methodName);
        boolean builder = classIsEnlistedAsBuilder(getterOrSetterMethod);
        if (isGetterMethod(getterOrSetterMethod)
                || isSetterMethod(getterOrSetterMethod)) {
            return Introspector.decapitalize(builder ? methodName : methodName.substring(prefix.length()));
        }
        return null;
    }

}
