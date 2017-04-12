package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public interface FieldPrinter {

    void appendTo(StringBuilder stringBuilder, ObjectNode objectNode);
}