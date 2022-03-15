import java.util.ArrayList;
import java.util.List;

public class Class extends JavaElement {
    private boolean isAbstract;
    private boolean isSubclass;
    private String superName;
    private List<InstanceVariable> instanceVariables;


    public Class() {
    }

    @Override
    public Class fromSignature(String signature) {
        return SignatureToObject.classFromSignature(signature);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(final boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public List<InstanceVariable> getInstanceVariables() {
        return instanceVariables;
    }

    public void setInstanceVariables(final List<InstanceVariable> instanceVariables) {
        this.instanceVariables = instanceVariables;
    }

    public boolean isSubclass() {
        return isSubclass;
    }

    public void setSubclass(final boolean subclass) {
        isSubclass = subclass;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(final String superName) {
        this.superName = superName;
    }

    @Override
    public String toString() {
        return "Class{"
                + "isAbstract=" + isAbstract
                + ", isSubclass=" + isSubclass
                + ", superName='" + superName + '\''
                + ", instanceVariables=" + instanceVariables
                + ", visibility=" + visibility
                + ", name='" + name + '\''
                + ", isFinal=" + isFinal
                + '}';
    }
}
