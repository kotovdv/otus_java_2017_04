package ru.otus.homework02.measurer;

import ru.otus.homework02.measurer.tool.ObjectDeepSizeMeter;
import ru.otus.homework02.measurer.tool.printer.ObjectTreePrinter;
import ru.otus.homework02.measurer.tool.result.ObjectTree;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitriy Kotov
 */
public class App {

    private ObjectDeepSizeMeter deepSizeMeter = new ObjectDeepSizeMeter();

    public static void main(String[] args) {
        new App().startShowcase();
    }

    public void startShowcase() {
        handleEmptyArray();
        handleEmptyString();
        handleSimpleObject();
        List<Integer> arrayList = handleEmptyArrayList();
        handleFilledArrayList(arrayList);
        handleFilledHashMap();
        handleFilledArray();
    }

    private void handleEmptyString() {
        doHandling("measure empty string", "");
    }

    private void handleEmptyArray() {
        doHandling("measure empty array", new int[]{});
    }

    private void handleSimpleObject() {
        doHandling("measure basic object", new Object());
    }

    private List<Integer> handleEmptyArrayList() {
        List<Integer> emptyArrayList = new ArrayList<>();
        doHandling("measure empty array list", emptyArrayList);

        return emptyArrayList;
    }

    private void handleFilledArrayList(List<Integer> list) {
        list.add(500);
        list.add(1000);
        list.add(1500);

        doHandling("measure filled array list", list);
    }

    private void handleFilledHashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(300, "300");
        map.put(400, "400");
        map.put(500, "500");

        doHandling("measure filled hash map", map);
    }

    private void handleFilledArray() {
        doHandling("measure filled array", new int[]{1, 3, 5, 7, 9});
    }

    private void doHandling(@Nonnull String jobName, @Nonnull Object value) {
        System.out.println("*********************");
        System.out.println("Handling [" + jobName + "]");

        ObjectTree objectTree = deepSizeMeter.measure(value);
        System.out.println("Total object size [" + objectTree.getTotalSize() + "]");
        ObjectTreePrinter.printTree(System.out, objectTree);
    }
}
