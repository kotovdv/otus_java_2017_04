package ru.otus.homework2;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    private static Map<Object, Object> map = new WeakHashMap<>();

    private static long SLEEP_DURATION = 100;

    @Test
    public void testFunctionality() throws Exception {
        Object obj = new Object();

        long startingMemory = getMemoryUse();
        map.put(obj, null);
        obj = null;
        System.gc();
        System.runFinalization();

        while (map.size() != 0) {

        }
        System.out.println(startingMemory - getMemoryUse());

    }


    private static long getMemoryUse() {
        putOutTheGarbage();
        long totalMemory = Runtime.getRuntime().totalMemory();
        putOutTheGarbage();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return (totalMemory - freeMemory);
    }

    private static void putOutTheGarbage() {
        collectGarbage();
        collectGarbage();
    }

    private static void collectGarbage() {
        try {
            System.gc();
            Thread.sleep(SLEEP_DURATION);

            System.runFinalization();
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
