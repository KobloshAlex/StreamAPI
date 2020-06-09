package com.foxminded.stream;

import com.foxminded.stream.file.FileParser;
import com.foxminded.stream.file.FileParserImpl;
import com.foxminded.stream.file.FileReaderImpl;
import com.foxminded.stream.file.FileReader;
import com.foxminded.stream.provider.StatisticProvider;
import com.foxminded.stream.view.RacerView;
import com.foxminded.stream.view.RacerViewImpl;
import com.foxminded.stream.validator.Validator;
import com.foxminded.stream.validator.ValidatorImpl;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        final File abbreviations = getFile(classloader, "abbreviations.txt");
        final File starts = getFile(classloader, "start.log");
        final File ends = getFile(classloader, "end.log");

        final Validator validator = new ValidatorImpl();
        final FileReader fileReader = new FileReaderImpl();
        final FileParser fileParser = new FileParserImpl();
        final RacerView racerView = new RacerViewImpl();

        final StatisticProvider statisticProvider = new StatisticProvider(validator, fileReader, fileParser, racerView);

        System.out.println(statisticProvider.provideStatistics(starts, ends, abbreviations));
    }

    private static File getFile(ClassLoader classloader, String fileName) {
        return new File(Objects.requireNonNull(classloader.getResource(fileName)).getFile());
    }
}
