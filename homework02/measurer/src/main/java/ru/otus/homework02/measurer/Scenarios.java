package ru.otus.homework02.measurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Arrays.asList;

public class Scenarios {

    private static final List<Scenario> SCENARIOS = asList(
            new Scenario("measure empty string", ""),
            new Scenario("measure empty array", new int[]{}),
            new Scenario("measure basic object", new Object()),
            new Scenario("measure empty array list", new ArrayList<>()),
            new Scenario("measure filled array list", new ArrayList<>(
                    asList(500,
                            1000,
                            1500)
            )),
            new Scenario("measure filled hash map", new HashMap<>(
                    of(
                            "300", 300,
                            "400", 400,
                            "500", 500)
            )),
            new Scenario("measure filled array", new int[]{1, 3, 5, 7, 9})
    );

    public static void forEach(Consumer<? super Scenario> consumer) {
        SCENARIOS.forEach(consumer);
    }
}