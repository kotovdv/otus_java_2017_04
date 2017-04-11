package ru.otus.homework02.measurer.tool;

import org.junit.Test;
import ru.otus.homework02.measurer.tool.subject.GenericObject;
import ru.otus.homework02.measurer.tool.subject.SingleFieldClass;
import ru.otus.homework02.measurer.tool.subject.inheritance.Child;
import ru.otus.homework02.measurer.tool.tools.JammBasedMeter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
public class ObjectDeepSizeMeterTest {

    private ObjectDeepSizeMeter objectDeepSizeMeter = new ObjectDeepSizeMeter();

    @Test
    public void testEmptyArray() throws Exception {
        assertMeasurements(() -> new Object[]{});
    }

    @Test
    public void testObject() throws Exception {
        assertMeasurements(Object::new);
    }

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

    @Test
    public void testLinkedListHandling() throws Exception {
        assertMeasurements(() -> {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(380);
            list.add(400);

            return list;
        });
    }

    @Test
    public void testTreeSetHandling() throws Exception {
        assertMeasurements(() -> {
            TreeSet<Integer> set = new TreeSet<>();
            set.addAll(Collections.singletonList(500));

            return set;
        });
    }


    @Test
    public void testHugeArrayList() throws Exception {
        assertMeasurements(() -> {
            List<Integer> randomNumbers = new ArrayList<>();

            for (int i = 0; i < 2000; i++) {
                randomNumbers.add(ThreadLocalRandom.current().nextInt(200, 2000));
            }
            return randomNumbers;
        });
    }


    @Test
    public void testArrayWithStrings() throws Exception {
        assertMeasurements(() ->
                new String[]{
                        "a1b2",
                        "a1b2",
                        "a1b2",
                });
    }

    @Test
    public void testMultidimensionalArray() throws Exception {
        assertMeasurements(() -> new String[][]{
                {"Str11", "Str12", "Str13"},
                {"Str21", "Str22", "Str13"},
                {"Str31", "Str32", "Str33"},

                {"Str11", "Str21", "Str31"},
                {"Str12", "Str22", "Str32"},
                {"Str13", "Str22", "Str33"},
        });
    }


    @Test
    public void testEmptyString() throws Exception {
        assertMeasurements(() -> "");
    }

    @Test
    public void testAnotherEmptyString() throws Exception {
        assertMeasurements(() -> new String(""));
    }

    private void assertMeasurements(Supplier<?> myClassSupplier) {
        long customMeterValue = objectDeepSizeMeter.measure(myClassSupplier.get()).getTotalSize();
        long jammMeterValue = JammBasedMeter.measure(myClassSupplier);

        assertThat(customMeterValue).isEqualTo(jammMeterValue);
    }
}

