package com.foxminded.stream.file;

import com.foxminded.stream.domain.Racer;

import java.util.List;

public interface FileParser {
    List<Racer> getRaceData(List<String> startData, List<String> endData, List<String> abbreviationData);
}
