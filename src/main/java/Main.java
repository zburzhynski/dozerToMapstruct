import model.DozerConfig;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        InputStream is = MapstructGenerator.class.getResourceAsStream("dozerBeanMapping.xml");
        DozerConfigParser dozerParser = new DozerConfigParser();
        DozerConfig dozerConfig = dozerParser.parse(is);
        new MapstructGenerator(dozerConfig).generate();
    }

}
