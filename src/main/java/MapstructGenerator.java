import model.DozerConfig;
import model.Field;
import model.Mapping;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapstruct code generator.
 */
public class MapstructGenerator {

    private static final String MAPSTRUCT_IMPORT = "import org.mapstruct.Mapping;";
    private static final String IMPORT_TEMPLATE = "import %s;";
    private static final String MAPPER_NAME = "Mapper";
    private static final String MAPPER_INTERFACE = CommonConstant.JAVA_INTERFACE + CommonConstant.SPACE + MAPPER_NAME + CommonConstant.SPACE + CommonConstant.LEFT_BRACKET;
    private static final String METHOD_TEMPLATE = "%s to%s(%s %s);";
    private static final String ANNOTATION_TEMPLATE = "@Mapping(%s = \"%s\", target = \"%s\")";
    private static final String ANNOTATION_WITH_QUALIFIER_TEMPLATE = "@Mapping(%s = \"%s\", target = \"%s\"/*, qualifiedByName = \"%s\"*/)";
    private static final String CONSTANT_PREFIX = "class.simpleName";
    private static final String CONSTANT = "constant";
    private static final String SOURCE = "source";

    private DozerConfig dozerConfig;
    private FileWriter writer;

    public MapstructGenerator(DozerConfig dozerConfig) {
        this.dozerConfig = dozerConfig;
    }

    public void generate() throws IOException {
        writer = new FileWriter(MAPPER_NAME + CommonConstant.DOT + CommonConstant.JAVA_EXTENSION);
        generateImports(dozerConfig);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(MAPPER_INTERFACE);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
        for (Mapping mapping : dozerConfig.getMappings()) {
            generateMethod(mapping);
        }
        writer.write(CommonConstant.RIGHT_BRACKET);
        writer.close();
    }

    private void generateImports(DozerConfig dozerConfig) throws IOException {
        writer.write(MAPSTRUCT_IMPORT);
        writer.write(CommonConstant.LINE_BREAK);
        Set<String> unique = dozerConfig.getMappings().stream().map(Mapping::getClassA).collect(Collectors.toSet());
        for (String mapping : unique) {
            String importName = String.format(IMPORT_TEMPLATE, mapping);
            writer.write(importName);
            writer.write(CommonConstant.LINE_BREAK);
        }
    }

    private void generateMethod(Mapping mapping) throws IOException {
        String sourceClass = mapping.getClassA();
        String targetClass = mapping.getClassB();
        String sourceEntityName = sourceClass.substring(sourceClass.lastIndexOf(CommonConstant.DOT) + 1);
        String targetEntityName = targetClass.substring(targetClass.lastIndexOf(CommonConstant.DOT) + 1);
        generateAnnotation(mapping);
        String method = String.format(METHOD_TEMPLATE, targetClass, targetEntityName, sourceEntityName,
            StringUtils.uncapitalize(sourceEntityName));
        writer.append(CommonConstant.TAB_SPACE);
        writer.write(method);
        writer.write(CommonConstant.LINE_BREAK);
        writer.write(CommonConstant.LINE_BREAK);
    }

    private void generateAnnotation(Mapping mapping) throws IOException {
        String sourceClass = mapping.getClassA();
        String sourceEntityName = sourceClass.substring(sourceClass.lastIndexOf(CommonConstant.DOT) + 1);
        if (CollectionUtils.isNotEmpty(mapping.getFields())) {
            Set<Field> uniqueMapping = new LinkedHashSet<>(mapping.getFields());
            for (Field field : uniqueMapping) {
                long count = uniqueMapping.stream().map(Field::getA).filter(a -> a.equals(field.getA())).count();
                if (!field.getA().equals(field.getB()) || count > 1) {
                    String annotation;
                    String template = StringUtils.isBlank(field.getConverter()) ? ANNOTATION_TEMPLATE : ANNOTATION_WITH_QUALIFIER_TEMPLATE;
                    if (field.getA().equals(CONSTANT_PREFIX)) {
                        annotation = String.format(template, CONSTANT, StringUtils.capitalize(sourceEntityName), StringUtils.uncapitalize(field.getB()), field.getConverter());
                    } else {
                        annotation = String.format(template, SOURCE, StringUtils.uncapitalize(field.getA()), StringUtils.uncapitalize(field.getB()), field.getConverter());
                    }
                    writer.append(CommonConstant.TAB_SPACE);
                    writer.write(annotation);
                    writer.write(CommonConstant.LINE_BREAK);
                }
            }
        }
    }

}
