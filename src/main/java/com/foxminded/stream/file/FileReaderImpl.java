package com.foxminded.stream.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderImpl implements FileReader {

    @Override
    public List<String> read(File file) {

        try {
            return Files.lines(Paths.get(file.getAbsolutePath())).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to read file");
        }
    }
}
