import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SignatureToObject {
    private static final String PUBLIC = "public";
    private static final String PROTECTED = "protected";
    private static final String PRIVATE = "private";
    private static final String ABSTRACT = "abstract";
    private static final String FINAL = "final";
    private static final String CLASS = "class";
    private static final String EXTENDS = "extends";
    private static final String IMPLEMENTS = "implements";
    private static final String STATIC = "static";


    private static String tokenExistsPattern(final String token) {
        return ".*" + token + ".*";
    }

    private static String whatsAfterTokenPattern(final String token) {
        return "(?<=" + token + " )(\\w)+";
    }

    private static String firstThatIsNotTokenPattern(final String[] tokens) {
        String pattern = "\\b(?!";
        for (String token : tokens) {
            pattern += token + "|";
        }
        pattern += " \\b)\\w+";
        return pattern;

    }

    private static Visibility visibilityFromSignature(final String signature) {
        if (Pattern.matches(tokenExistsPattern(PUBLIC), signature)) {
            return Visibility.PUBLIC;
        }
        if (Pattern.matches(tokenExistsPattern(PRIVATE), signature)) {
            return Visibility.PRIVATE;
        }
        if (Pattern.matches(tokenExistsPattern(PROTECTED), signature)) {
            return Visibility.PROTECTED;
        }
        return Visibility.DEFAULT;
    }

    public static Class classFromSignature(final String signature) throws NoClassSignatureFound {
        if (!Pattern.matches(tokenExistsPattern(CLASS), signature)) {
            throw new NoClassSignatureFound();
        }

        final Pattern classNamePattern = Pattern.compile(whatsAfterTokenPattern(CLASS));
        final Matcher classNameMatcher = classNamePattern.matcher(signature);
        classNameMatcher.find();
        final Class newClass = new Class();
        newClass.setName(classNameMatcher.group());
        newClass.setAbstract(Pattern.matches(tokenExistsPattern(ABSTRACT), signature));
        newClass.setVisibility(visibilityFromSignature(signature));
        newClass.setSubclass(Pattern.matches(tokenExistsPattern(EXTENDS), signature));
        if (newClass.isSubclass()) {
            final Pattern superNamePattern = Pattern.compile(whatsAfterTokenPattern(EXTENDS));
            final Matcher superNameMatcher = superNamePattern.matcher(signature);
            superNameMatcher.find();
            newClass.setSuperName(superNameMatcher.group());
        }
        return newClass;
    }

    public static InstanceVariable instanceVariableFromSignature(final String signature) {
        final InstanceVariable instanceVariable = new InstanceVariable();
        final String pattern = firstThatIsNotTokenPattern(new String[]{PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL});
        final Pattern typeAndNamePattern = Pattern.compile(pattern);
        final Matcher typeAndNameMatcher = typeAndNamePattern.matcher(signature);
        if (!typeAndNameMatcher.find()) {
            throw new IllegalArgumentException("Could not find instance variable type.");
        }
        instanceVariable.setType(typeAndNameMatcher.group());
        if(!typeAndNameMatcher.find()) {
            throw new IllegalArgumentException("Could not find instance variable name.");
        }
        instanceVariable.setName(typeAndNameMatcher.group());
        instanceVariable.setVisibility(visibilityFromSignature(signature));
        instanceVariable.setFinal(Pattern.matches(tokenExistsPattern(FINAL), signature));
        instanceVariable.setStatic(Pattern.matches(tokenExistsPattern(STATIC), signature));

        return instanceVariable;
    }


    public static void main(final String[] args) {
        final String classSignature = "public abstract class SignatureToObject extends foo";
        Class newClass = classFromSignature(classSignature);
        final String instanceVariableSignature = "private static final String CLASS = \"class\";";
        InstanceVariable instanceVariable = instanceVariableFromSignature(instanceVariableSignature);
        System.out.println(newClass);
        System.out.println(instanceVariable);
    }

    public static class NoClassSignatureFound extends IllegalArgumentException {

    }
}
