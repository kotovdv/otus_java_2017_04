package ru.otus.homework02.measurer;

import ru.otus.homework02.measurer.tool.ObjectDeepSizeMeter;
import ru.otus.homework02.measurer.tool.printer.ObjectTreePrinter;
import ru.otus.homework02.measurer.tool.result.ObjectTree;

/**
 * @author Dmitriy Kotov
 */
public class App {

    private ObjectDeepSizeMeter deepSizeMeter = new ObjectDeepSizeMeter();

    public static void main(String[] args) {
        new App().startShowcase();
    }

    public void startShowcase() {
        Scenarios.forEach(this::doHandling);
    }

    private void doHandling(Scenario scenario) {
        System.out.println("*********************");
        System.out.println("Handling [" + scenario.getScenarioName() + "]");

        ObjectTree objectTree = deepSizeMeter.measure(scenario.getMeasurementTarget());
        System.out.println("Total object size [" + objectTree.getTotalSize() + "]");
        ObjectTreePrinter.printTree(System.out, objectTree);
    }
}
