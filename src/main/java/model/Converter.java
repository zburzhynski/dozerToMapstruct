package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Converter.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Converter implements Serializable {

    @XmlElement(name = "class-a")
    private String classA;

    @XmlElement(name = "class-b")
    private String classB;

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

}
