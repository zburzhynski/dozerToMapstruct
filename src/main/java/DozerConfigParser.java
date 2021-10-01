import model.DozerConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Dozer configuration parser.
 */
public class DozerConfigParser {

    public DozerConfig parse(InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DozerConfig.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (DozerConfig) unmarshaller.unmarshal(is);
    }

}
