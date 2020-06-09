package com.foxminded.stream.validator;

import java.io.File;

public class ValidatorImpl implements Validator {

    private static final String FILE_NOT_FOUND = " file not found at ";
    private static final String FILE_IS_EMPTY = " file is empty at ";

    @Override
    public void validate(File start, File end, File abbreviation) {

        ensureFileExist(abbreviation, isNotExist(abbreviation), FILE_NOT_FOUND);
        ensureFileExist(start, isNotExist(start), FILE_NOT_FOUND);
        ensureFileExist(end, isNotExist(end), FILE_NOT_FOUND);
        ensureFileExist(abbreviation, isEmpty(abbreviation), FILE_IS_EMPTY);
        ensureFileExist(start, isEmpty(start), FILE_IS_EMPTY);
        ensureFileExist(end, isEmpty(end), FILE_IS_EMPTY);
    }

    private void ensureFileExist(File file, boolean notExist, String message) {
        if (notExist) {
            throw new IllegalArgumentException(file.getName() + message + file.getAbsolutePath());
        }
    }

    private boolean isEmpty(File file) {
        return file.length() == 0;
    }

    private boolean isNotExist(File file) {
        return !file.exists();
    }
}
