package com.foxminded.stream.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<String> read(File fileName) throws IOException;
}
