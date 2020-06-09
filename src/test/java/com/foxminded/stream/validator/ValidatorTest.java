package com.foxminded.stream.validator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    private final Validator validator = new ValidatorImpl();
    private final File noFile = new File("");
    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private final File abbreviations = getFile(classloader, "abbreviations.txt");
    private final File starts = getFile(classloader, "start.log");
    private final File ends = getFile(classloader, "end.log");
    private final File empty = getFile(classloader, "empty.txt");

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfAbbreviationsFileIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(empty, starts, ends));

        assertTrue(exception.getMessage().contentEquals(empty.getName() + " file is empty at " + empty.getAbsolutePath()));
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfStartFileIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(abbreviations, empty, ends));

        assertTrue(exception.getMessage().contentEquals(empty.getName() + " file is empty at " + empty.getAbsolutePath()));
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfEndFileIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(abbreviations, starts, empty));

        assertTrue(exception.getMessage().contentEquals(empty.getName() + " file is empty at " + empty.getAbsolutePath()));
    }

    @Test
    void validatorShouldNotThrowException() {
        assertDoesNotThrow(() -> validator.validate(abbreviations, starts, ends));
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIFFileWasNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(noFile, starts, ends));

        assertTrue(exception.getMessage().contentEquals(noFile.getName() + " file not found at " + noFile.getAbsolutePath()));
    }

    private static File getFile(ClassLoader classloader, String fileName) {
        return new File(Objects.requireNonNull(classloader.getResource(fileName)).getFile());
    }
}
