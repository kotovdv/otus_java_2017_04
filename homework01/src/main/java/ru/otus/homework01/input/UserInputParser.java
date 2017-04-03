package ru.otus.homework01.input;

import org.apache.commons.cli.*;
import ru.otus.homework01.exception.FailedToParseUserInputException;

import java.nio.file.Paths;

/**
 * @author Dmitriy Kotov
 */
public class UserInputParser {

    private static final String DEFAULT_VALUE = "titanic_crash.csv";
    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();

    public UserInputParser() {
        Option path = Option.builder("path")
                .argName("path")
                .hasArg()
                .desc("path to casualties .csv file")
                .build();

        options.addOption(path);
    }

    public UserInput parse(String[] args) {
        try {
            CommandLine commandLine = parser.parse(options, args);
            String path = commandLine.getOptionValue("path", DEFAULT_VALUE);

            return new UserInput(Paths.get(path));
        } catch (ParseException e) {
            throw new FailedToParseUserInputException(e);
        }
    }
}
