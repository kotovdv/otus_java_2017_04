package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class ValuePrinter implements FieldPrinter {

    @Override
    public void appendTo(StringBuilder stringBuilder, ObjectNode objectNode) {
        if (isPrimitiveField(objectNode)) {
            stringBuilder.append("Value = [")
                    .append(objectNode.getValue())
                    .append("]");
        }
    }

    private boolean isPrimitiveField(ObjectNode node) {
        return node.getFieldType().isPrimitive();
    }
}