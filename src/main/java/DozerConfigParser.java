import model.Mappings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Dozer config parser.
 */
public class DozerConfigParser {

    public Mappings parse(InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Mappings.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Mappings) unmarshaller.unmarshal(is);
    }

}
