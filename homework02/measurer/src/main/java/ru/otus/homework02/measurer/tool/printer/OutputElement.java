package ru.otus.homework02.measurer.tool.printer;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

class OutputElement {
    private final int shiftLevel;
    private final ObjectNode node;

    OutputElement(ObjectNode node, int shiftLevel) {
        this.shiftLevel = shiftLevel;
        this.node = node;
    }

    public int getShiftLevel() {
        return shiftLevel;
    }

    public ObjectNode getNode() {
        return node;
    }
}