package ru.otus.homework01.model;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import static java.util.Collections.unmodifiableCollection;

/**
 * @author Dmitriy Kotov
 */
public class Casualties {

    private LinkedHashMultimap<Outcome, Person> data = LinkedHashMultimap.create();

    public Casualties(Multimap<Outcome, Person> data) {
        this.data.putAll(data);
    }

    public Iterable<Person> getSaved() {
        return unmodifiableCollection(data.get(Outcome.SAVED));
    }

    public Iterable<Person> getLost() {
        return unmodifiableCollection(data.get(Outcome.LOST));
    }
}
