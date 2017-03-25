package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class RerunnableBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final BiFunction<T, U, R> function;
    private final int numberOfPossibleAttempts;

    public RerunnableBiFunction(@Nonnull final BiFunction<T, U, R> function, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.function = Objects.requireNonNull(function);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input1, final U input2) {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        while (numberOfAttempts < numberOfPossibleAttempts) {
            try {
                return function.apply(input1, input2);
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        }

        throw new RerunnableException("Could not successfully run function!", exceptions);
    }

    public static <T, U, R> RerunnableBiFunctionBuilderStepOne attempt(@Nonnull final BiFunction<T, U, R> function) {
        return new RerunnableBiFunctionBuilderStepOne(Objects.requireNonNull(function));
    }

    public static final class RerunnableBiFunctionBuilderStepOne<T, U, R> {
        private final BiFunction<T, U, R> function;

        public RerunnableBiFunctionBuilderStepOne(@Nonnull final BiFunction<T, U, R> function) {
            this.function = Objects.requireNonNull(function);
        }

        public RerunnableBiFunction<T, U, R> times(final int numberOfPossibleAttempts) {
            return new RerunnableBiFunction<>(function, numberOfPossibleAttempts);
        }
    }
}
