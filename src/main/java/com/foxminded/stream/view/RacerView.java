package com.foxminded.stream.view;

import com.foxminded.stream.domain.Racer;

import java.util.List;

public interface RacerView {
    String provideView(List<Racer> raceData);
}
