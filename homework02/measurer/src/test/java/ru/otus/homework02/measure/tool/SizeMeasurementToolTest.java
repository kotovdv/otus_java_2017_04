package ru.otus.homework02.measure.tool;

import org.junit.Test;
import ru.otus.homework02.CustomSizeMeasurer;
import ru.otus.homework02.measure.tool.subject.GenericObject;
import ru.otus.homework02.measure.tool.subject.SingleFieldClass;
import ru.otus.homework02.measure.tool.subject.inheritance.Child;

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

    @Test
    public void testObjectWithInheritance() throws Exception {
        assertMeasurements(() ->
                new Child(80, 50, 22)
        );
    }

    @Test
    public void testArrayWithRandomElements() throws Exception {
        assertMeasurements(() -> {
                    HashMap<Object, Object> map = new HashMap<>();
                    map.put(300, 350);
                    return new Object[]{
                            new Object(),
                            new Object(),
                            map
                    };
                }
        );
    }

    private void assertMeasurements(Supplier<?> myClassSupplier) {
        long actual = sizeMeasurementTool.measure(myClassSupplier.get()).getTotalSize();
        long expected = CustomSizeMeasurer.measure(myClassSupplier);

        assertThat(expected).isEqualTo(actual);
    }
}

