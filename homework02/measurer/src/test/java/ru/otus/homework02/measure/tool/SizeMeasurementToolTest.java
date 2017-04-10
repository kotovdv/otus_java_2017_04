package ru.otus.homework02.measure.tool;

import org.junit.Test;
import ru.otus.homework02.CustomSizeMeasurer;
import ru.otus.homework02.measure.tool.result.Result;
import ru.otus.homework02.measure.tool.subject.GenericObject;
import ru.otus.homework02.measure.tool.subject.SingleFieldClass;

import java.util.HashMap;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
public class SizeMeasurementToolTest {

    private SizeMeasurementTool sizeMeasurementTool = new SizeMeasurementTool();

    @Test
    public void testIntegerSize() throws Exception {
        assertMeasurements(() -> 300);
    }

    @Test
    public void testDoubleSize() throws Exception {
        assertMeasurements(() -> 300D);
    }

    @Test
    public void testCustomClass() throws Exception {
        assertMeasurements(SingleFieldClass::new);
    }

    @Test
    public void testHashMapComparison() throws Exception {
        assertMeasurements(HashMap::new);
    }

    @Test
    public void testRecursivelyLinkedObject() throws Exception {
        assertMeasurements(() -> {
            GenericObject parent = new GenericObject();
            parent.setGenericObject(parent);

            return parent;
        });
    }

    private void assertMeasurements(Supplier<?> myClassSupplier) {
        Result measure = sizeMeasurementTool.measure(myClassSupplier.get());
        measure.print();

        long actual = measure.getTotalSize();
        long expected = CustomSizeMeasurer.measure(myClassSupplier);

        assertThat(expected).isEqualTo(actual);
    }
}

