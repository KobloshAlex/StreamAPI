package com.foxminded.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {
    private final FileReader fileReader = new FileReaderImpl();
    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    @Test
    void readShouldReturnListFromGivenFile() throws IOException {
        List<String> expected = Arrays.asList("DRR2018-05-24_12:15:24.067",
                "SVF2018-05-24_12:04:03.332",
                "LHM2018-05-24_12:19:32.585");

        assertEquals(expected, fileReader.read(new File(classloader.getResource("end.log").getFile())));
    }
}
