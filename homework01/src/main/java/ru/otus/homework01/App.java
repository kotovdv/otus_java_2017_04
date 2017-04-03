package ru.otus.homework01;

import ru.otus.homework01.input.UserInput;
import ru.otus.homework01.input.UserInputParser;
import ru.otus.homework01.model.Casualties;

import java.nio.file.Path;

import static ru.otus.homework01.parser.CasualtiesParser.parse;

/**
 * @author Dmitriy Kotov
 */
public class App {

    public static void main(String[] args) {
        UserInputParser inputParser = new UserInputParser();
        UserInput input = inputParser.parse(args);

        displayCasualtiesInformation(input.getPath());
    }

    private static void displayCasualtiesInformation(Path path) {
        Casualties casualties = parse(path);
        System.out.printf("**Parsing casualties from [%s]**%n", path);

        System.out.println("**Saved**");
        casualties.getSaved().forEach(System.out::println);

        System.out.println("**Lost**");
        casualties.getLost().forEach(System.out::println);
    }
}
