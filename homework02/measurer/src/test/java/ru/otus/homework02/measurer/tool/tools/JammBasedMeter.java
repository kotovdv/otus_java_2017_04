package ru.otus.homework02.measurer.tool.tools;

import com.ea.agentloader.AgentLoader;
import org.github.jamm.MemoryMeter;

import java.util.function.Supplier;

public class JammBasedMeter {

    static {
        AgentLoader.loadAgentClass("org.github.jamm.MemoryMeter", "");
    }

    public static long measure(Supplier<?> supplier) {
        return new JammBasedMeter().measureSize(supplier);
    }

    private long measureSize(Supplier<?> supplier) {
        MemoryMeter memoryMeter = new MemoryMeter();

        return memoryMeter.measureDeep(supplier.get());
    }
}