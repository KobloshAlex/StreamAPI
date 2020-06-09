package com.foxminded.stream.view;

import com.foxminded.stream.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class RacerViewImpl implements RacerView {
    private static final String DURATION_FORMAT = "%2d:%02d.%03d";
    private static final int TOP_FIFTEEN_RACERS = 15;
    private static final char SPACE = ' ';
    private static final String VERTICAL_LINE = "|";
    private static final char DASH = '-';
    private static final int FIRST_NINE_POSITIONS = 9;

    @Override
    public String provideView(List<Racer> racers) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < racers.size(); i++) {
            if (i < FIRST_NINE_POSITIONS) {
                stringBuilder.append(SPACE);
            }
            if (i == TOP_FIFTEEN_RACERS) {
                stringBuilder.append(drawDashLine(racers)).append("\n");
            }
            stringBuilder.append(i + 1).append(SPACE)
                    .append(drawRacerLine(racers.get(i), getLength(racers, Racer::getName), getLength(racers, Racer::getTeam))).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String drawDashLine(List<Racer> racers) {
        return fillEndOfString("-", racers.size() + getLength(racers, Racer::getTeam) + getLength(racers, Racer::getTeam), DASH);
    }

    private String drawRacerLine(Racer racer, int nameLength, int teamLength) {
        return fillEndOfString(racer.getName(), nameLength, SPACE) + VERTICAL_LINE +
                fillEndOfString(racer.getTeam(), teamLength, SPACE) + VERTICAL_LINE + getDurationFormat(racer);
    }

    private String getDurationFormat(Racer racer) {
        Duration duration = racer.getDuration();
        return String.format(DURATION_FORMAT,
                duration.toMinutes(),
                duration.getSeconds(),
                duration.toMillis());
    }

    private String fillEndOfString(String string, int quantity, char symbol) {
        StringBuilder result = new StringBuilder(string);
        for (int i = 0; i < quantity - string.length(); i++) {
            result.append(symbol);
        }
        return result.toString();
    }

    private int getLength(List<Racer> racers, Function<Racer, String> mapper) {
        return racers.stream()
                .map(mapper)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }
}
