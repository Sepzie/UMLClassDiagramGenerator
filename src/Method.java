import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Method extends InstanceVariable {
    public record Parameter(String type, String name) {
    }

    List<Parameter> parameters;

    public Method(InstanceVariable that) {
        this.visibility = that.getVisibility();
        this.isFinal = that.isFinal();
        this.name = that.getName();
        this.type = that.getType();
        this.isStatic = that.isStatic();
        parameters = new ArrayList<>();
    }

    public Method() {
    }

    @Override
    public Method fromSignature(final String signature) {
        return SignatureToObject.methodFromSignature(signature);
    }

    public void addParameter(final Parameter parameter) {
        parameters.add(parameter);
    }

    public void addParameter(final String type, final String name) {
        parameters.add(new Parameter(type, name));
    }

    public boolean removeParameter(final Parameter parameter) {
        return parameters.remove(parameter);
    }

    public boolean removeParameter(final String name) {
        Iterator<Parameter> iterator = parameters.iterator();
        Parameter parameter;
        boolean isRemoved = false;
        while (iterator.hasNext() && !isRemoved) {
            parameter = iterator.next();
            if (parameter.name().equals(name)) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Method{"
                + "isStatic=" + isStatic
                + ", type='" + type + '\''
                + ", visibility=" + visibility
                + ", name='" + name + '\''
                + ", isFinal=" + isFinal
                + ", parameters=" + parameters
                + '}';
    }
}
