package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RerunnableFunction<T, R> implements Function<T, R> {

    private final Function<T, R> function;

    private final int numberOfPossibleAttempts;

    public RerunnableFunction(@Nonnull final Function<T, R> function, final int numberOfPossibleAttempts) {
        this.function = function;
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input) {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        while (numberOfAttempts < numberOfPossibleAttempts) {
            try {
                return function.apply(input);
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        }

        throw new RerunnableException("Could not successfully run function!", exceptions);
    }

    public static <T, R> RerunnableFunctionBuilderStepOne doTry(@Nonnull final Function<T, R> function) {
        return new RerunnableFunctionBuilderStepOne(function);
    }

    public static final class RerunnableFunctionBuilderStepOne<T, R> {

        private final Function<T, R> function;

        public RerunnableFunctionBuilderStepOne(@Nonnull final Function<T, R> function) {
            this.function = function;
        }

        public RerunnableFunction<T, R> times(final int numberOfPossibleAttempts) {
            return new RerunnableFunction<>(function, numberOfPossibleAttempts);
        }
    }
}
