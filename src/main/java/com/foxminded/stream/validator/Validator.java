package com.foxminded.stream.validator;

import java.io.File;

public interface Validator {
    void validate(File start, File end, File abbreviation);
}
