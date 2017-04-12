package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class TypePrinter extends SimpleFieldPrinter {
    public TypePrinter() {
        super("Type");
    }

    @Override
    protected String getValue(ObjectNode objectNode) {
        return selectType(objectNode).getSimpleName();
    }

    private Class<?> selectType(ObjectNode node) {
        return node.getValue() == null
                ? node.getFieldType()
                : node.getInstanceType();
    }
}