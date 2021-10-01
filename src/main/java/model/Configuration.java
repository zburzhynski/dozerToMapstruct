package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.List;

/**
 * Configuration.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration implements Serializable {

    @XmlElement(name = "date-format")
    private String dateFormat;

    @XmlElementWrapper(name = "custom-converters")
    @XmlElement(name = "converter")
    private List<Converter> converters;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public void setConverters(List<Converter> converters) {
        this.converters = converters;
    }

}
