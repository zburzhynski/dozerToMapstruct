import model.DozerConfig;
import model.Mapping;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Extra methods mapstruct generator.
 */
public class ExtraMethodGenerator {

    private static final String MAPPER_NAME = "ExtraMethodMapper";
    private static final String MAPPER_INTERFACE = "public interface ExtraMethodMapper" + CommonConstant.SPACE + CommonConstant.LEFT_BRACKET;
    private static final String METHOD_TEMPLATE = "List<%s.%s> to%sList(List<%s> %sList);";

    private DozerConfig dozerConfig;
    private String dtoPackage;
    private FileWriter writer;

    public ExtraMethodGenerator(DozerConfig dozerConfig, String dtoPackage) {
        this.dozerConfig = dozerConfig;
        this.dtoPackage = dtoPackage;
    }

    public void generate() throws IOException {
        writer = new FileWriter(MAPPER_NAME + CommonConstant.DOT + CommonConstant.JAVA_EXTENSION);
        writer.write(MAPPER_INTERFACE);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
        for (Mapping mapping : dozerConfig.getMappings()) {
            generateMethod(mapping);
        }
        writer.write(CommonConstant.RIGHT_BRACKET);
        writer.close();
    }

    private void generateMethod(Mapping mapping) throws IOException {
        String sourceClass = mapping.getClassA();
        String sourceEntityName = sourceClass.substring(sourceClass.lastIndexOf(CommonConstant.DOT) + 1);
        String method = String.format(METHOD_TEMPLATE, dtoPackage, sourceEntityName, sourceEntityName, sourceEntityName,
            StringUtils.uncapitalize(sourceEntityName));
        writer.append(CommonConstant.TAB_SPACE);
        writer.write(method);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
    }

}
