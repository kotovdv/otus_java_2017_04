package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class SizePrinter extends SimpleFieldPrinter {
    public SizePrinter() {
        super("Size");
    }

    @Override
    protected String getValue(ObjectNode objectNode) {
        return Long.toString(selectSize(objectNode));
    }

    private long selectSize(ObjectNode node) {
        return node.getBranchSize() == 0
                ? node.getPersonalSize()
                : node.getBranchSize();
    }
}