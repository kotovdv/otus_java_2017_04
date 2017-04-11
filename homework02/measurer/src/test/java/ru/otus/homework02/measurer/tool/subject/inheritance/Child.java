package ru.otus.homework02.measurer.tool.subject.inheritance;

/**
 * @author Dmitriy Kotov
 */
public class Child extends Parent {

    private final int childAge;
    private Object randomField1 = new Object();

    public Child(int grandParentAge, int parentAge, int childAge) {
        super(grandParentAge, parentAge);
        this.childAge = childAge;
    }
}
