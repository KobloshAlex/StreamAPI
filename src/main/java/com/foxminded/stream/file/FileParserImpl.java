package com.foxminded.stream.file;

import com.foxminded.stream.domain.Racer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileParserImpl implements FileParser {

    private static final String SPLIT_REGEX = "_";
    private static final int SELECT_ABBREVIATION = 0;
    private static final int SELECT_NAME = 1;
    private static final int SELECT_TEAM = 2;
    private static final int ABBREVIATION_BEGIN_INDEX = 0;
    private static final int ABBREVIATION_END_INDEX = 3;
    private static final DateTimeFormatter DAY_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    @Override
    public List<Racer> getRaceData(List<String> startData, List<String> endData, List<String> abbreviationData) {

        Map<String, LocalDateTime> parsedStartData = getStringLocalDateTimeMap(startData);

        Map<String, LocalDateTime> parsedEndData = getStringLocalDateTimeMap(endData);

        return abbreviationData.stream().map(str -> this.getRacer(str, parsedStartData, parsedEndData))
                .sorted(Comparator.comparing(Racer::getDuration)).collect(Collectors.toList());
    }

    private Map<String, LocalDateTime> getStringLocalDateTimeMap(List<String> fileName) {
        return fileName.stream().collect(Collectors.toMap(line -> line.substring(ABBREVIATION_BEGIN_INDEX, ABBREVIATION_END_INDEX),
                line -> LocalDateTime.parse(line.substring(ABBREVIATION_END_INDEX), FileParserImpl.DAY_TIME_FORMAT)));
    }

    private Racer getRacer(String line, Map<String, LocalDateTime> starts, Map<String, LocalDateTime> ends) {
        String[] racerData = line.split(SPLIT_REGEX);
        String abbreviation = racerData[SELECT_ABBREVIATION];
        String name = racerData[SELECT_NAME];
        String team = racerData[SELECT_TEAM];
        LocalDateTime end = ends.get(abbreviation);
        LocalDateTime start = starts.get(abbreviation);

        return Racer.builder()
                .withName(name)
                .withTeam(team)
                .withEnd(end)
                .withStart(start)
                .build();
    }
}
