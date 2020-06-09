package com.foxminded.stream.provider;

import com.foxminded.stream.domain.Racer;
import com.foxminded.stream.file.FileParser;
import com.foxminded.stream.file.FileReader;
import com.foxminded.stream.validator.Validator;
import com.foxminded.stream.view.RacerView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StatisticProvider {
    private final Validator validator;
    private final FileReader fileReader;
    private final FileParser fileParser;
    private final RacerView racerView;

    public StatisticProvider(Validator validator, FileReader fileReader, FileParser fileParser, RacerView racerView) {
        this.validator = validator;
        this.fileReader = fileReader;
        this.fileParser = fileParser;
        this.racerView = racerView;
    }

    public String provideStatistics(File startFile, File endFile, File abbreviationFile) throws IOException {
        validator.validate(startFile, endFile, abbreviationFile);

        List<String> start = fileReader.read(startFile);
        List<String> abbreviation = fileReader.read(abbreviationFile);
        List<String> end = fileReader.read(endFile);

        final List<Racer> racers = fileParser.getRaceData(start, end, abbreviation);

        return racerView.provideView(racers);
    }
}
