package ru.otus.homework02;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public class CustomSizeMeasurer {

    private static Map<Object, Object> map = new WeakHashMap<>();

    private static long SLEEP_DURATION = 100;

    public static long measure(Supplier<?> supplier) {
        return new CustomSizeMeasurer().measureSize(supplier);
    }

    private long measureSize(Supplier<?> supplier) {
        Object obj = supplier.get();

        long startingMemory = getMemoryUse();
        map.put(obj, null);
        obj = null;
        System.gc();
        System.runFinalization();

        waitForObjectToDisappear();
        return startingMemory - getMemoryUse();
    }

    private void waitForObjectToDisappear() {
        while (map.size() != 0) {

        }
    }


    private long getMemoryUse() {
        putOutTheGarbage();
        long totalMemory = Runtime.getRuntime().totalMemory();
        putOutTheGarbage();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return (totalMemory - freeMemory);
    }

    private void putOutTheGarbage() {
        collectGarbage();
        collectGarbage();
    }

    private void collectGarbage() {
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
