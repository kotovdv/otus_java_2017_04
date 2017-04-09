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

            outputBuilder
                    .append(shift(currentShift))
                    .append("Name = [")
                    .append(currentNode.getField().getName())
                    .append("]")
                    .append("\t")
                    .append("Type = [")
                    .append(currentNode.getField().getType())
                    .append("]")
                    .append("\t")
                    .append("\t")
                    .append("Size in bytes =[")
                    .append(currentNode.getPersonalSize())
                    .append("]")
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

}