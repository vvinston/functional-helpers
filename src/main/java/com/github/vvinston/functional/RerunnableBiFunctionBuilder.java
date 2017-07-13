package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public final class RerunnableBiFunctionBuilder<INPUT1, INPUT2, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> function;

    RerunnableBiFunctionBuilder(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function) {
        this.function = Objects.requireNonNull(function);
    }

    public BiFunction<INPUT1, INPUT2, RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiFunction<>(function, numberOfPossibleAttempts);
    }
}
