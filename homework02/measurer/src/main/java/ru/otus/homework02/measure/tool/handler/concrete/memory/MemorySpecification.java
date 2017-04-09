package ru.otus.homework02.measure.tool.handler.concrete.memory;

import ru.otus.homework02.exception.UnableToDetermineJVMBitness;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

/**
 * @author Dmitriy Kotov
 */
public enum MemorySpecification {

    JVM_32_BIT(12L, 8L, 8L, 4L),
    JVM_64_OOPS_ENABLED(16L, 12L, 8L, 4L),
    JVM_64_OOPS_DISABLED(24L, 16L, 8L, 8L);

    private static final long OOPS_GIGABYTES_THRESHOLD = 30L * 1024 * 1024 * 1024;

    private final long arrayHeaderSize;
    private final long objectHeaderSize;
    private final long objectPaddingSize;
    private final long referenceSize;

    MemorySpecification(long arrayHeaderSize,
                        long objectHeaderSize,
                        long objectPaddingSize,
                        long referenceSize) {
        this.arrayHeaderSize = arrayHeaderSize;
        this.objectHeaderSize = objectHeaderSize;
        this.objectPaddingSize = objectPaddingSize;
        this.referenceSize = referenceSize;
    }


    public static MemorySpecification getCurrentSpecification() {
        String bitness = getBitness();

        switch (bitness) {
            case "64": {
                return isCompressionEnabled()
                        ? JVM_64_OOPS_ENABLED
                        : JVM_64_OOPS_DISABLED;
            }

            case "32": {
                return JVM_32_BIT;
            }
            default: {
                throw new UnableToDetermineJVMBitness(String.format("Unexpected bitness value [%s]", bitness));
            }
        }
    }

    private static boolean isCompressionEnabled() {
        return maxMemory() < OOPS_GIGABYTES_THRESHOLD && javaVersion() > 17;
    }

    private static int javaVersion() {
        String javaVersionStr = System.getProperty("java.specification.version");
        javaVersionStr = javaVersionStr.replace(".", "");

        return Integer.parseInt(javaVersionStr);
    }

    private static long maxMemory() {
        long maxMemory = 0;
        for (MemoryPoolMXBean mp : ManagementFactory.getMemoryPoolMXBeans()) {
            maxMemory += mp.getUsage().getMax();
        }

        return maxMemory;
    }


    private static String getBitness() {
        String bitness = System.getProperty("sun.arch.data.model");
        if (bitness == null) {
            throw new UnableToDetermineJVMBitness("sun.arch.data.model is empty");
        }

        return bitness;
    }

    public long getArrayHeaderSize() {
        return arrayHeaderSize;
    }

    public long getObjectHeaderSize() {
        return objectHeaderSize;
    }

    public long getObjectPaddingSize() {
        return objectPaddingSize;
    }

    public long getReferenceSize() {
        return referenceSize;
    }
}
