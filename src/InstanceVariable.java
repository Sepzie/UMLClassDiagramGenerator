public class InstanceVariable extends JavaElement {
    protected boolean isStatic;
    protected String type;

    public InstanceVariable() {
    }

    public InstanceVariable fromSignature(final String signature) {
        return SignatureToObject.instanceVariableFromSignature(signature);
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(final boolean aStatic) {
        isStatic = aStatic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InstanceVariable{"
                + "visibility=" + visibility
                + ", isStatic=" + isStatic
                + ", isFinal=" + isFinal
                + ", type='" + type + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
