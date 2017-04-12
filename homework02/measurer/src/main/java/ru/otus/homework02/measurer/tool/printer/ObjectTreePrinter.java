package ru.otus.homework02.measurer.tool.printer;

import ru.otus.homework02.measurer.tool.printer.field.*;
import ru.otus.homework02.measurer.tool.result.ObjectNode;
import ru.otus.homework02.measurer.tool.result.ObjectTree;

import javax.annotation.Nonnull;
import java.io.PrintStream;
import java.util.*;

import static java.util.Collections.nCopies;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.toList;

/**
 * @author Dmitriy Kotov
 */
public class ObjectTreePrinter {

    private static final int SHIFT_STEP = 3;
    private Map<Integer, String> shiftsCache = new HashMap<>();

    private List<FieldPrinter> printersPipeline = Arrays.asList(
            new IdPrinter(),
            new NamePrinter(),
            new TypePrinter(),
            new SizePrinter(),
            new ValuePrinter(),
            new DuplicatePrinter()
    );

    public static void printTree(@Nonnull PrintStream printStream, @Nonnull ObjectTree objectTree) {
        new ObjectTreePrinter().print(printStream, objectTree);
    }

    public void print(@Nonnull PrintStream printStream, @Nonnull ObjectTree objectTree) {
        Deque<OutputElement> outputQueue = createOutputQueue(objectTree);

        StringBuilder outputBuilder = new StringBuilder();

        OutputElement currentOutput;
        while ((currentOutput = outputQueue.poll()) != null) {
            ObjectNode currentNode = currentOutput.getNode();
            int currentShift = currentOutput.getShiftLevel();

            outputBuilder.append(startingShift(currentShift));

            for (FieldPrinter currentPrinter : printersPipeline) {
                currentPrinter.appendTo(outputBuilder, currentNode);

                outputBuilder.append("\t").append("\t");
            }

            outputBuilder.append("\n");

            List<ObjectNode> children = currentNode.getChildren();

            List<OutputElement> childrenOutput = prepareChildOutput(currentShift, children);
            childrenOutput.forEach(outputQueue::addFirst);
        }

        printStream.print(outputBuilder.toString());
    }

    private Deque<OutputElement> createOutputQueue(@Nonnull ObjectTree objectTree) {
        Deque<OutputElement> outputQueue = new LinkedList<>();
        outputQueue.add(new OutputElement(objectTree.getRootNode(), 0));

        return outputQueue;
    }

    private List<OutputElement> prepareChildOutput(int currentShift, List<ObjectNode> children) {
        List<OutputElement> collect = children
                .stream()
                .map(element -> (new OutputElement(element, currentShift + SHIFT_STEP)))
                .collect(toList());

        reverse(collect);

        return collect;
    }


    private String startingShift(int shiftLevel) {
        return shiftsCache.computeIfAbsent(shiftLevel, l ->
                String.join("", nCopies(l, "\t")
                )
        );
    }
}