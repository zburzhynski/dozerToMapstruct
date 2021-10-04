package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * Field.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Field implements Serializable {

    private String a;

    private String b;

    @XmlAttribute(name = "custom-converter")
    private String converter;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getConverter() {
        return converter;
    }

    public void setConverter(String converter) {
        this.converter = converter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Field)) {
            return false;
        }

        Field that = (Field) o;
        return new EqualsBuilder()
            .append(a, that.a)
            .append(b, that.b)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(a)
            .append(b)
            .toHashCode();
    }

}
