package org.example.repositories.load;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.example.models.PersonModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public final class PeopleLoadRepository implements LoadRepository<PersonModel> {

    private static final String CSV = "/people.csv";

    private static List<PersonModel> getCustomerModels(File input) {
        int skipHeader = 1;

        try (FileReader fileReader = new FileReader(input);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(skipHeader).build()) {

            return StreamSupport.stream(csvReader.spliterator(), true)
                                .distinct()
                                .filter(strings -> strings.length == 6)
                                .filter(strings -> IntStream.range(0, 5)
                                                            .noneMatch(value -> Objects.isNull(strings[value])
                                                                             || "".equals(strings[value])
                                                                             || "null".equals(strings[value])))
                                .map(strings -> {
                                    try {
                                        return new PersonModel(Integer.parseInt(strings[0]),
                                                               strings[1],
                                                               strings[2],
                                                               Integer.parseInt(strings[3]),
                                                               strings[4],
                                                               strings[5]);
                                    } catch (Exception e) {
                                        return null;
                                    }
                                })
                                .filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            throw new CantReadFileException(e);
        }
    }

    @Override
    public List<PersonModel> load() {
        try {
            File input = Paths.get(Objects.requireNonNull(PeopleLoadRepository.class.getResource(CSV)).toURI()).toFile();
            return getCustomerModels(input);
        } catch (URISyntaxException e) {
            throw new CantReadFileException(e);
        }
    }
}
