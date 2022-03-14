import java.util.ArrayList;
import java.util.List;

public class Class {
    private Visibility visibility;
    private String name;
    private boolean isAbstract;
    private boolean isSubclass;
    private String superName;
    private List<InstanceVariable> instanceVariables;

    public Class(final Visibility visibility, final String name, final boolean isAbstract, final boolean isSubclass,
                 final String superName, final List<InstanceVariable> instanceVariables) {
        this.visibility = visibility;
        this.name = name;
        this.isAbstract = isAbstract;
        this.isSubclass = isSubclass;
        this.superName = superName;
        this.instanceVariables = instanceVariables;
    }


    public Class(final String name) {
        visibility = Visibility.DEFAULT;
        this.name = name;
        isAbstract = false;
        isSubclass = false;
        instanceVariables = new ArrayList<>();

    }

    public Class() {
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public void setSubclass(boolean subclass) {
        isSubclass = subclass;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    @Override
    public String toString() {
        return "Class{"
                + "visibility=" + visibility
                + ", name='" + name + '\''
                + ", isAbstract=" + isAbstract
                + ", isSubclass=" + isSubclass
                + ", superName='" + superName + '\''
                + ", instanceVariables=" + instanceVariables
                + '}';
    }
}
