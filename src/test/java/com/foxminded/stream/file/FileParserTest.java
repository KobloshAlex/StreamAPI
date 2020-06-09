package com.foxminded.stream.file;

import com.foxminded.stream.domain.Racer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileParserTest {
    private final FileParser fileParser = new FileParserImpl();

    @Test
    void getRacerDataShouldReturnParsedDataInList() {
        List<String> startData = Collections.singletonList("DRR2018-05-24_12:14:12.054");
        List<String> endData = Collections.singletonList("DRR2018-05-24_12:15:24.067");
        List<String> abbreviationData = Collections.singletonList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");

        Racer racer = Racer.builder()
                .withName("Daniel Ricciardo")
                .withTeam("RED BULL RACING TAG HEUER")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067"))
                .build();
        List<Racer> expected = Collections.singletonList(racer);

        assertEquals(expected, fileParser.getRaceData(startData, endData, abbreviationData));

    }
}
