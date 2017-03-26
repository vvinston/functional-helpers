package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public final class RerunnableBiFunctionBuilder<T, U, R> {
    private final BiFunction<T, U, R> function;

    RerunnableBiFunctionBuilder(@Nonnull final BiFunction<T, U, R> function) {
        this.function = Objects.requireNonNull(function);
    }

    public BiFunction<T, U, R> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiFunction<>(function, numberOfPossibleAttempts);
    }
}
