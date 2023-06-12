package org.example.repositories.write;

import com.opencsv.CSVWriter;
import org.example.models.JoinModel;
import org.example.repositories.load.PeopleLoadRepository;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class JoinRepository implements WriteRepository<JoinModel> {

    private static final String CSV = "/output.csv";

    private static void writeLines(@NotNull List<JoinModel> lines, File output) {
        //TODO: You have 3 min to find and fix the bad practices on this method, and develop the TODOs
        try {
            //TODO: If the output file contains data, it must be deleted, and new data added.
            FileWriter fileWriter = new FileWriter(output, true);
            //! SEE:    Ask me if you never used the OpenCSV before.
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            //TODO: Don't forget the header (use the name COUNTRY for the merge key column).
            String[] header = new String[]{"#ID", "NAME", "GENDER", "AGE", "DATE", "COUNTRY", "Company", "Email"};
            csvWriter.writeNext(header);

            //! Without logic issue on this block to write the header and each line
            lines.stream()
                    .map(joinModel -> new String[]{String.valueOf(joinModel.getId()), joinModel.getName(), joinModel.getGender(), String.valueOf(joinModel.getAge()), joinModel.getDate(), joinModel.getCountry(), joinModel.getCompany(), joinModel.getEmail()})
                    .forEach(csvWriter::writeNext);
        } catch (IOException e) {
            throw new CantWriteFileException(e);
        }
    }

    @Override
    public void save(List<JoinModel> lines) {
        try {
            File output = Paths.get(Objects.requireNonNull(PeopleLoadRepository.class.getResource(CSV)).toURI()).toFile();
            writeLines(lines, output);
        } catch (URISyntaxException e) {
            throw new CantWriteFileException(e);
        }
    }
}
