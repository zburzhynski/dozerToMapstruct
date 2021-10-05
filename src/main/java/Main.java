import model.DozerConfig;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private static final String DOZER_CONFIG = "dozerBeanMapping.xml";
    private static final String DTO_PACKAGE = "ru.lanit.hcs.nsi.api.dto";

    public static void main(String[] args) throws JAXBException, IOException {
        InputStream is = MapstructGenerator.class.getResourceAsStream(DOZER_CONFIG);
        DozerConfigParser dozerParser = new DozerConfigParser();
        DozerConfig dozerConfig = dozerParser.parse(is);
        new MapstructGenerator(dozerConfig).generate();
        new ExtraMethodGenerator(dozerConfig, DTO_PACKAGE).generate();
    }

}
