package ru.otus.homework01.parser;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.homework01.exception.FailedToLoadCasualtiesException;
import ru.otus.homework01.model.Casualties;
import ru.otus.homework01.model.Outcome;
import ru.otus.homework01.model.Person;
import ru.otus.homework01.model.Sex;
import ru.otus.homework01.parser.validation.*;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.nio.file.Files.notExists;
import static java.util.Arrays.asList;
import static org.apache.commons.csv.CSVFormat.DEFAULT;

/**
 * @author Dmitriy Kotov
 */
public class CasualtiesParser {

    private static final List<CSVFieldValidator> validationPipeline = asList(
            new NameFieldValidator(),
            new SexFieldValidator(),
            new AgeFieldValidator(),
            new OutcomeFieldValidator()
    );

    public static Casualties parse(@Nonnull Path accidentFile) {
        return new CasualtiesParser().parseCasualties(accidentFile);
    }

    public Casualties parseCasualties(@Nonnull Path accidentFile) {
        try (InputStream stream = getInputStream(accidentFile);
             InputStreamReader reader = new InputStreamReader(stream);
             CSVParser csvRecords = new CSVParser(reader, DEFAULT.withFirstRecordAsHeader())) {

            Multimap<Outcome, Person> casualtiesData = LinkedHashMultimap.create();

            csvRecords.forEach(addToMultimap(casualtiesData));

            return new Casualties(casualtiesData);
        } catch (IOException e) {
            throw new FailedToLoadCasualtiesException(accidentFile.toString(), e);
        }
    }

    @Nonnull
    private InputStream getInputStream(Path accidentFile) throws FileNotFoundException {
        if (accidentFile.isAbsolute()) {
            if (notExists(accidentFile)) {
                throw new FailedToLoadCasualtiesException(
                        String.format("Unable to locate accident file located at path [%s]",
                                accidentFile.toAbsolutePath())
                );
            }

            return new FileInputStream(accidentFile.toFile());
        } else {
            InputStream stream = getSystemResourceAsStream(accidentFile.toString());
            if (stream == null) {
                throw new FailedToLoadCasualtiesException(
                        String.format("Unable to locate accident file located at relative path [%s]",
                                accidentFile.toString())
                );
            }

            return stream;
        }
    }

    private Consumer<CSVRecord> addToMultimap(Multimap<Outcome, Person> casualtiesData) {
        return currentRecord -> {
            validateRecord(currentRecord);

            casualtiesData.put(
                    parseOutcome(currentRecord),
                    parsePerson(currentRecord)
            );
        };
    }

    private void validateRecord(CSVRecord record) {
        validationPipeline.forEach(validator -> validator.validate(record));
    }

    private Outcome parseOutcome(CSVRecord record) {
        return Outcome.valueOf(cleanseValue(record.get("Outcome")));
    }

    private Person parsePerson(CSVRecord record) {
        return new Person(
                record.get("Name"),
                Sex.valueOf(record.get("Sex").toUpperCase().trim()),
                Integer.valueOf(record.get("Age"))
        );
    }

    private String cleanseValue(String value) {
        return value.toUpperCase().trim();
    }
}