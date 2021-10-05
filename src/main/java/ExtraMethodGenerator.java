import model.DozerConfig;
import model.Mapping;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Extra methods mapstruct generator.
 */
public class ExtraMethodGenerator {

    private static final String LIST_IMPORT = "import java.util.List;";
    private static final String IMPORT_TEMPLATE = "import %s;";
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
        Set<String> sourceClasses = dozerConfig.getMappings().stream().map(Mapping::getClassA).collect(Collectors.toSet());
        generateImports(sourceClasses);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(MAPPER_INTERFACE);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
        for (String source : sourceClasses) {
            generateMethod(source);
        }
        writer.write(CommonConstant.RIGHT_BRACKET);
        writer.close();
    }

    private void generateImports(Set<String> sourceClasses) throws IOException {
        writer.write(LIST_IMPORT);
        writer.write(CommonConstant.LINE_BREAK);
        for (String source : sourceClasses) {
            String importName = String.format(IMPORT_TEMPLATE, source);
            writer.write(importName);
            writer.write(CommonConstant.LINE_BREAK);
        }
    }

    private void generateMethod(String source) throws IOException {
        String sourceEntityName = source.substring(source.lastIndexOf(CommonConstant.DOT) + 1);
        String method = String.format(METHOD_TEMPLATE, dtoPackage, sourceEntityName, sourceEntityName, sourceEntityName,
            StringUtils.uncapitalize(sourceEntityName));
        writer.append(CommonConstant.TAB_SPACE);
        writer.write(method);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
    }

}
