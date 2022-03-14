public class InstanceVariable {
    private Visibility visibility;
    private boolean isStatic;
    private boolean isFinal;
    private String type;
    private String name;

    public InstanceVariable(final Visibility visibility, final boolean isStatic, final boolean isFinal,
                            final String type, final String name) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
    }

    public InstanceVariable(final String type, final String name) {
        visibility = Visibility.DEFAULT;
        isStatic = false;
        isFinal = false;
        this.type = type;
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
