package ru.otus.homework02.measurer.tool.tools;

import java.util.function.Supplier;

/**
 * @author Dmitriy Kotov
 */
@Deprecated
public class FreeMemoryBasedMeter {

    private static long SLEEP_DURATION = 250;

    public long measureSize(Supplier<?> supplier) {
        Object obj = supplier.get();
        long startingMemory = getMemoryUse();

        obj = null;
        System.gc();
        System.runFinalization();

        return startingMemory - getMemoryUse();
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
