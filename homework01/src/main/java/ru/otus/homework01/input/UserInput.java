package ru.otus.homework01.input;

import java.nio.file.Path;

/**
 * @author Dmitriy Kotov
 */
public class UserInput {

    private final Path path;

    public UserInput(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
