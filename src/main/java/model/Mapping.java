package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Mapping.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Mapping implements Serializable {

    @XmlElement(name = "class-a")
    private String classA;

    @XmlElement(name = "class-b")
    private String classB;

    @XmlElement(name = "field")
    private List<Field> fields;

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }

    public String getClassB() {
        return classB;
    }

    public void setClassB(String classB) {
        this.classB = classB;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
