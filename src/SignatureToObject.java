import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SignatureToObject {
    private static final String PUBLIC = "public";
    private static final String PROTECTED = "protected";
    private static final String PRIVATE = "final";
    private static final String ABSTRACT = "abstract";
    private static final String FINAL = "final";
    private static final String CLASS = "class";
    private static final String EXTENDS = "extends";
    private static final String IMPLEMENTS = "implements";
    private static final String STATIC = "static";


    private static String existsPattern(final String token) {
        return ".*" + token + ".*";
    }

    private static String whatsAfterPattern(final String token) {
        return "(?<=" + token + " )(\\w|_)+";
    }

    private static Visibility getVisibility(final String signature) {
        if (Pattern.matches(existsPattern(PUBLIC), signature)) {
            return Visibility.PUBLIC;
        }
        if (Pattern.matches(existsPattern(PRIVATE), signature)) {
            return Visibility.PRIVATE;
        }
        if (Pattern.matches(existsPattern(PROTECTED), signature)) {
            return Visibility.PROTECTED;
        }
        return Visibility.DEFAULT;
    }

    static Class getClass(final String signature) throws NoClassSignatureFound {
        if (!Pattern.matches(existsPattern(CLASS), signature)) {
            throw new NoClassSignatureFound();
        }

        final Pattern classNamePattern = Pattern.compile(whatsAfterPattern(CLASS));
        final Matcher classNameMatcher = classNamePattern.matcher(signature);
        classNameMatcher.find();
        final Class newClass = new Class();
        newClass.setName(classNameMatcher.group());
        newClass.setAbstract(Pattern.matches(existsPattern(ABSTRACT), signature));
        newClass.setVisibility(getVisibility(signature));
        newClass.setSubclass(Pattern.matches(existsPattern(EXTENDS), signature));
        if (newClass.isSubclass()) {
            final Pattern superNamePattern = Pattern.compile(whatsAfterPattern(EXTENDS));
            final Matcher superNameMatcher = superNamePattern.matcher(signature);
            superNameMatcher.find();
            newClass.setSuperName(superNameMatcher.group());
        }
        return newClass;
    }

    public static void main(final String[] args) {
        final String signature = "public final class SignatureToObject";
        Class newClass = getClass(signature);
        System.out.println(newClass);
    }

    public static class NoClassSignatureFound extends IllegalArgumentException {

    }
}
