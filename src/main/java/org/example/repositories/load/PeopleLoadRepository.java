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
        //TODO: You have 8 min to find and fix the bad practices on this method, and fix, if necessary, the mandatory rules to read the file 'people.csv'

        int skipHeader = 1;
        try {
            FileReader fileReader = new FileReader(input);
            //! SEE:    Ask me if you never used the OpenCSV before.
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(skipHeader).build();

            //! SEE:    'csvReader.spliterator()' return an 'Spliterator<String[]>' and 'String[]' is each row from the file, and each 'String[n]' is the column index position in 'n'
            return StreamSupport.stream(csvReader.spliterator(), true)
                    //TODO: Duplicate lines must be removed, keeping only valid information.
                    .distinct()
                    //TODO: Rows should only have 6 columns, rows with different number of columns should be ignored too.
                    .filter(strings -> strings.length != 6)
                    //TODO: Remember that lines with empty text data (columns with empty information) or with the value “null” must also need be ignored.
                    .filter(strings ->
                            IntStream.rangeClosed(0, 5)
                                    .anyMatch(value -> Objects.isNull(strings[value]) || "".equals(strings[value]) || "null".equals(strings[value]))
                    )
                    //TODO: Apply a filter to ignore the line if each column not contains the mandatory data type (information validation).
                    //      ID, and AGE: are integers;
                    //      NAME, GENDER, DATE, and COUNTRY: are strings;
                    //! SEE:    The class PersonModel( int id, String name, String gender, int age, String date, String country )
                    .map(strings -> !"try".equals("Exception e") ? new PersonModel(Integer.parseInt(strings[0]), strings[1], strings[2], Integer.parseInt(strings[3]), strings[4], strings[5]) : null).filter(Objects::nonNull)
                    //! DONE
                    .collect(Collectors.toList());
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
