import model.DozerConfig;
import model.Field;
import model.Mapping;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapstruct code generator.
 */
public class MapstructGenerator {

    private static final String DOT = ".";
    private static final String SPACE = " ";
    private static final String LEFT_BRACKET = "{";
    private static final String RIGHT_BRACKET = "}";
    private static final String TAB_SPACE = "    ";
    private static final String LINE_BREAK = "\n";
    private static final String MAPSTRUCT_IMPORT = "import org.mapstruct.Mapping;";
    private static final String IMPORT_TEMPLATE = "import %s;";
    private static final String METHOD_TEMPLATE = "%s to%s(%s %s);";
    private static final String ANNOTATION_TEMPLATE = "@Mapping(%s = \"%s\", target = \"%s\")";
    private static final String CONSTANT_PREFIX = "class.simpleName";
    private static final String CONSTANT = "constant";
    private static final String SOURCE = "source";
    private static final String MAPPER_NAME = "Mapper.java";
    private static final String MAPPER_INTERFACE = "public interface Mapper" + SPACE + LEFT_BRACKET;

    private DozerConfig dozerConfig;

    public MapstructGenerator(DozerConfig dozerConfig) {
        this.dozerConfig = dozerConfig;
    }

    public void generate() throws IOException {
        //TODO: 1. custom converter
        //TODO: 2. NsiEntryCreateRequest clarify
        FileWriter writer = new FileWriter(MAPPER_NAME);
        generateImports(writer, dozerConfig);
        writer.write(LINE_BREAK);
        writer.write(MAPPER_INTERFACE);
        writer.write(LINE_BREAK);
        writer.write(LINE_BREAK);
        for (Mapping mapping : dozerConfig.getMappings()) {
            generateMethod(writer, mapping);
        }
        writer.write(RIGHT_BRACKET);
        writer.close();
    }

    private void generateImports(FileWriter writer, DozerConfig dozerConfig) throws IOException {
        writer.write(MAPSTRUCT_IMPORT);
        writer.write(LINE_BREAK);
        Set<String> unique = dozerConfig.getMappings().stream().map(Mapping::getClassA).collect(Collectors.toSet());
        for (String mapping : unique) {
            String importName = String.format(IMPORT_TEMPLATE, mapping);
            writer.write(importName);
            writer.write(LINE_BREAK);
        }
    }

    private void generateMethod(FileWriter writer, Mapping mapping) throws IOException {
        String sourceClass = mapping.getClassA();
        String targetClass = mapping.getClassB();
        String sourceEntityName = sourceClass.substring(sourceClass.lastIndexOf(DOT) + 1);
        String targetEntityName = targetClass.substring(targetClass.lastIndexOf(DOT) + 1);
        if (CollectionUtils.isNotEmpty(mapping.getFields())) {
            for (Field field : mapping.getFields()) {
                String annotation;
                if (field.getA().equals(CONSTANT_PREFIX)) {
                    annotation = String.format(ANNOTATION_TEMPLATE, CONSTANT, sourceEntityName, field.getB());
                } else {
                    annotation = String.format(ANNOTATION_TEMPLATE, SOURCE, field.getA(), field.getB());
                }
                writer.append(TAB_SPACE);
                writer.write(annotation);
                writer.write(LINE_BREAK);
            }
        }
        String method = String.format(METHOD_TEMPLATE, targetClass, targetEntityName, sourceEntityName, StringUtils.uncapitalize(sourceEntityName));
        writer.append(TAB_SPACE);
        writer.write(method);
        writer.write(LINE_BREAK);
        writer.write(LINE_BREAK);
    }

}
