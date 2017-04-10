package ru.otus.homework02.measure.tool.printer;

import ru.otus.homework02.measure.tool.result.ResultNode;

import javax.annotation.Nonnull;
import java.io.PrintStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.util.Collections.nCopies;

/**
 * @author Dmitriy Kotov
 */
public class SizeMeasurementResultPrinter {

    private static final int SHIFT_STEP = 3;
    private Map<Integer, String> shiftsCache = new HashMap<>();

    public static void printNodes(@Nonnull PrintStream printStream, @Nonnull ResultNode rootNode) {
        new SizeMeasurementResultPrinter().print(printStream, rootNode);
    }

    public void print(@Nonnull PrintStream printStream, @Nonnull ResultNode rootNode) {
        Deque<OutputElement> outputQueue = new LinkedList<>();
        outputQueue.add(new OutputElement(rootNode, 0));

        StringBuilder outputBuilder = new StringBuilder();

        OutputElement currentOutput;
        while ((currentOutput = outputQueue.poll()) != null) {
            ResultNode currentNode = currentOutput.getNode();
            int currentShift = currentOutput.getShiftLevel();

            outputBuilder.append(shift(currentShift))
                    .append(element("Id", currentNode.getId()))
                    .append("\t")
                    .append(element("Field name", currentNode.getFieldName()))
                    .append("\t")
                    .append(element("Instance type", currentNode.getInstanceType()))
                    .append("\t")
                    .append(element("Instance value", currentNode.getValue()))

                    .append("\t")
                    .append(currentNode.isDuplicate() ? "[DUPLICATE]" : "")
                    .append("\n");


            currentNode.getChildren().forEach(
                    element -> outputQueue.addLast(
                            new OutputElement(element, currentShift + SHIFT_STEP))
            );
        }

        printStream.print(outputBuilder.toString());
    }


    private String shift(int shiftLevel) {
        return shiftsCache.computeIfAbsent(shiftLevel, l ->
                String.join("", nCopies(l, "\t")
                )
        );
    }


    public String element(String name, Object value) {
        return name +
                "= [" +
                value +
                "]";
    }

}