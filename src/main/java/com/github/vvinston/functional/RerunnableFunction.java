package com.github.vvinston.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RerunnableFunction<T, R> implements Function<T, R> {

    private final Function<T, R> function;

    private final int numberOfPossibleRetries;

    public RerunnableFunction(final Function<T, R> function, final int numberOfPossibleRetries) {
        this.function = function;
        this.numberOfPossibleRetries = numberOfPossibleRetries;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input) {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        do {
            try {
                return function.apply(input);
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        } while (numberOfAttempts < numberOfPossibleRetries);

        throw new RerunnableException("Could not successfully run function!", exceptions);
    }
}
