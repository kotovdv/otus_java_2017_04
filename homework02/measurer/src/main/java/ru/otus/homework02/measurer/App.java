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
        new App().executeScenarios();
    }

    public void executeScenarios() {
        Scenarios.forEach(this::execute);
    }

    private void execute(Scenario scenario) {
        System.out.println("*********************");
        System.out.println("Executing scenario [" + scenario.getScenarioName() + "]");

        ObjectTree objectTree = deepSizeMeter.measure(scenario.getMeasurementTarget());
        System.out.println("Total object size [" + objectTree.getTotalSize() + "]");
        ObjectTreePrinter.printTree(System.out, objectTree);
    }
}
