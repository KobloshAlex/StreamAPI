package com.foxminded.stream.view;

import com.foxminded.stream.domain.Racer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RacerViewTest {
    private final RacerView racerView = new RacerViewImpl();

    @Test
    void provideViewShouldReturnCorrectString() {
        String expected = "1 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013";

        Racer racerOne = Racer.builder()
                .withName("Daniel Ricciardo")
                .withTeam("RED BULL RACING TAG HEUER")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067"))
                .build();
        List<Racer> racers = Collections.singletonList(racerOne);

        assertEquals(expected, racerView.provideView(racers));
    }

    @Test
    void provideViewShouldCreateHorizontalDelimiterAfterFifthPosition() {
        String expected = "1 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 2 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 3 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 4 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 5 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 6 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 7 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 8 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                " 9 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "10 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "11 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "12 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "13 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "14 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "15 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013\n" +
                "------------------------------------------------------------------\n" +
                "16 Daniel Ricciardo|RED BULL RACING TAG HEUER| 1:72.72013";

        List<Racer> racers = new ArrayList<>();

        for (int i = 0; i <= 15; i++) {
            Racer racerOne = Racer.builder()
                    .withName("Daniel Ricciardo")
                    .withTeam("RED BULL RACING TAG HEUER")
                    .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                    .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067"))
                    .build();

            racers.add(racerOne);
        }

        assertEquals(expected, racerView.provideView(racers));
    }
}
