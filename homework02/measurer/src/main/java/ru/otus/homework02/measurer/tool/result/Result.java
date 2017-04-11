package ru.otus.homework02.measurer.tool.result;

import ru.otus.homework02.measurer.tool.printer.SizeMeasurementResultPrinter;

import java.io.PrintStream;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * @author Dmitriy Kotov
 */
public class Result {

    private final LocalDateTime measurementDt;
    private final ResultNode rootNode;

    public Result(ResultNode rootNode) {
        this(now(), rootNode);
    }

    public Result(LocalDateTime measurementDt,
                  ResultNode node) {
        this.measurementDt = measurementDt;
        this.rootNode = node;
    }

    public void print() {
        print(System.out);
    }

    public void print(PrintStream outputStream) {
        SizeMeasurementResultPrinter.printNodes(outputStream, rootNode);
    }

    public long getTotalSize() {
        return rootNode.getBranchSize();
    }
}