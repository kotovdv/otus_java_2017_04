package ru.otus.homework02.measure;

import com.rits.cloning.Cloner;
import ru.otus.homework02.measure.output.MeasurementResult;

import java.util.function.Supplier;

/**
 * @author Dmitriy Kotov
 */
public class ObjectMeasurer {

    private final Cloner cloner = new Cloner();
    private final MeasurementStrategy strategy;

    public ObjectMeasurer(MeasurementStrategy strategy) {
        this.strategy = strategy;
    }

    public MeasurementResult safeMeasure(Supplier<?> supplier) {
        return measure(supplier.get());
    }

    public MeasurementResult safeMeasure(Object instance) {
        return measure(cloner.deepClone(instance));
    }

    private MeasurementResult measure(Object instance) {
        return new MeasurementResult();
    }
}
