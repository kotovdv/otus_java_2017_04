package ru.otus.homework02.measurer.tool.printer;

import ru.otus.homework02.measurer.tool.result.ResultNode;

class OutputElement {
    private final int shiftLevel;
    private final ResultNode node;

    OutputElement(ResultNode node, int shiftLevel) {
        this.shiftLevel = shiftLevel;
        this.node = node;
    }

    public static OutputElement from(ResultNode node, int shiftLevel) {
        return new OutputElement(node, shiftLevel);
    }

    public int getShiftLevel() {
        return shiftLevel;
    }

    public ResultNode getNode() {
        return node;
    }
}