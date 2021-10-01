package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Dozer configuration.
 */
@XmlRootElement(name = "mappings")
@XmlAccessorType(XmlAccessType.FIELD)
public class DozerConfig implements Serializable {

    @XmlElement(name = "configuration")
    private Configuration configuration;

    @XmlElement(name = "mapping")
    private List<Mapping> mappings;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }

}
