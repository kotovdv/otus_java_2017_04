package ru.otus.homework02.measure.tool;

import org.junit.Test;
import ru.otus.homework02.CustomSizeMeasurer;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
public class SizeMeasurementToolTest {

    private SizeMeasurementTool sizeMeasurementTool = new SizeMeasurementTool();

    @Test
    public void testIntegerSize() throws Exception {
        Supplier<Integer> integerSupplier = () -> 300;

        long expected = CustomSizeMeasurer.measure(integerSupplier);

        long actual = sizeMeasurementTool.measure(integerSupplier.get()).getTotalSize();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void testDoubleSize() throws Exception {
        Supplier<Double> integerSupplier = () -> 300D;

        long expected = CustomSizeMeasurer.measure(integerSupplier);

        long actual = sizeMeasurementTool.measure(integerSupplier.get()).getTotalSize();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void testCustomClass() throws Exception {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> jvmArgs = runtimeMXBean.getInputArguments();
        for (String arg : jvmArgs) {
            System.out.println(arg);
        }

        Supplier<MyClass> myClassSupplier = MyClass::new;

        long expected = CustomSizeMeasurer.measure(myClassSupplier);

        long actual = sizeMeasurementTool.measure(myClassSupplier.get()).getTotalSize();

        assertThat(expected).isEqualTo(actual);

    }

    class MyClass {
        private String emptyField;
    }
}