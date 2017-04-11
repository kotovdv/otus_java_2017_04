package ru.otus.homework02.measurer.tool.printer;

import ru.otus.homework02.measurer.tool.result.ObjectTree;
import ru.otus.homework02.measurer.tool.result.ResultNode;

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

    public static void printTree(@Nonnull PrintStream printStream, @Nonnull ObjectTree objectTree) {
        new ObjectTreePrinter().print(printStream, objectTree);
    }

    public void print(@Nonnull PrintStream printStream, @Nonnull ObjectTree objectTree) {
        printStream.println(String.format("Displaying object tree measured at [%s]", objectTree.getMeasurementDt()));

        Deque<OutputElement> outputQueue = new LinkedList<>();
        outputQueue.add(new OutputElement(objectTree.getRootNode(), 0));

        StringBuilder outputBuilder = new StringBuilder();

        OutputElement currentOutput;
        while ((currentOutput = outputQueue.poll()) != null) {
            ResultNode currentNode = currentOutput.getNode();
            int currentShift = currentOutput.getShiftLevel();

            outputBuilder
                    .append(shift(currentShift))
                    .append(element("Id", currentNode.getId()))
                    .append("\t")
                    .append(element("Name", currentNode.getFieldName()))
                    .append("\t")
                    .append(element("Type", selectType(currentNode)))
                    .append("\t");

            if (isPrimitiveField(currentNode)) {
                outputBuilder
                        .append(element("Value", currentNode.getValue()))
                        .append("\t");
            }

            outputBuilder
                    .append("\t")
                    .append(element("Size", currentNode.getBranchSize()))
                    .append("\t")
                    .append(currentNode.isDuplicate() ? "[DUPLICATE]" : "")
                    .append("\n");


            List<ResultNode> children = currentNode.getChildren();


            List<OutputElement> childrenOutput = prepareChildOutput(currentShift, children);
            childrenOutput.forEach(outputQueue::addFirst);
        }

        printStream.print(outputBuilder.toString());
    }

    private List<OutputElement> prepareChildOutput(int currentShift, List<ResultNode> children) {
        List<OutputElement> collect = children.stream().map(element -> (
                        new OutputElement(element, currentShift + SHIFT_STEP)
                )
        ).collect(toList());
        reverse(collect);

        return collect;
    }

    private boolean isPrimitiveField(ResultNode currentNode) {
        return currentNode.getFieldType().isPrimitive();
    }

    private Class<?> selectType(ResultNode currentNode) {
        return currentNode.getValue() == null
                ? currentNode.getFieldType()
                : currentNode.getInstanceType();
    }


    private String shift(int shiftLevel) {
        return shiftsCache.computeIfAbsent(shiftLevel, l ->
                String.join("", nCopies(l, "\t")
                )
        );
    }

    private String element(String name, Object value) {
        return name +
                "= [" +
                value +
                "]";
    }

}