package com.foxminded.stream.provider;

import com.foxminded.stream.domain.Racer;
import com.foxminded.stream.file.FileParser;
import com.foxminded.stream.file.FileReader;
import com.foxminded.stream.validator.Validator;
import com.foxminded.stream.view.RacerView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class StatisticProviderTest {
    private final File noFile = new File("");
    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private final File abbreviations = getFile(classloader, "abbreviations.txt");
    private final File starts = getFile(classloader, "start.log");
    private final File ends = getFile(classloader, "end.log");
    private final List<String> startData = new ArrayList<>();
    private final List<String> endData = new ArrayList<>();
    private final List<String> abbreviationData = new ArrayList<>();
    private final List<Racer> racers = new ArrayList<>();

    @InjectMocks
    private StatisticProvider statisticProvider;
    @Mock
    private Validator validator;
    @Mock
    private FileReader fileReader;
    @Mock
    private FileParser fileParser;
    @Mock
    private RacerView racerView;

    private static File getFile(ClassLoader classloader, String fileName) {
        return new File(Objects.requireNonNull(classloader.getResource(fileName)).getFile());
    }

    @Test
    void statisticProviderShouldSuccessfullyCallHisComponents() throws IOException {

        statisticProvider.provideStatistics(starts, ends, abbreviations);
        verify(validator).validate(starts, ends, abbreviations);
        verify(fileReader).read(starts);
        verify(fileParser).getRaceData(startData, endData, abbreviationData);
        verify(racerView).provideView(racers);
    }

    @Test
    void staticProviderShouldReturnIllegalArgumentException() {
        doThrow(new IllegalArgumentException(noFile.getName() + " file not found at " + noFile.getAbsolutePath()))
                .when(validator).validate(starts, ends, abbreviations);
        assertThrows(IllegalArgumentException.class, () -> statisticProvider.provideStatistics(starts, ends, abbreviations));
        verifyNoMoreInteractions(fileReader, fileParser, racerView);
    }
}
