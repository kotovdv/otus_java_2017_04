package ru.otus.homework02.measurer.tool.subject.inheritance;

/**
 * @author Dmitriy Kotov
 */
public class Parent extends Grandparent {
    private final int age;
    private Object randomField1 = new Object();

    public Parent(int grandParentAge, int age) {
        super(grandParentAge);
        this.age = age;
    }
}
