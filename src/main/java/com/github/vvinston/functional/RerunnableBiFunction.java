package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

final class RerunnableBiFunction<INPUT1, INPUT2, RESULT> implements BiFunction<INPUT1, INPUT2, RESULT> {

    private final BiFunction<INPUT1, INPUT2, RESULT> function;
    private final int numberOfPossibleAttempts;

    RerunnableBiFunction(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.function = Objects.requireNonNull(function);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public RESULT apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
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
}
