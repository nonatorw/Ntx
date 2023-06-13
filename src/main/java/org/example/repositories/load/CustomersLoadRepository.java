package org.example.repositories.load;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.example.models.CustomerModel;

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

public class CustomersLoadRepository implements LoadRepository<CustomerModel> {

    private static final String CSV = "/customers.csv";

    private static List<CustomerModel> getCustomerModels(File input) {
        int skipHeader = 1;

        try (FileReader fileReader = new FileReader(input);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(skipHeader).build()){

            return StreamSupport.stream(csvReader.spliterator(), true)
                            .distinct()
                            .filter(strings -> strings.length == 12)
                            .filter(strings -> IntStream.range(0, 11)
                            .noneMatch(value -> Objects.isNull(strings[value])
                                    || "".equals(strings[value])
                                    || "null".equals(strings[value])))
                                .map(strings -> new CustomerModel(strings[2], strings[4], strings[6], strings[9]))
                                .collect(Collectors.toList());

        } catch (IOException e) {
            throw new CantReadFileException(e);
        }
    }

    @Override
    public List<CustomerModel> load() {
        try {
            File input = Paths.get(Objects.requireNonNull(PeopleLoadRepository.class.getResource(CSV)).toURI()).toFile();
            return getCustomerModels(input);
        } catch (URISyntaxException e) {
            throw new CantReadFileException(e);
        }
    }
}
