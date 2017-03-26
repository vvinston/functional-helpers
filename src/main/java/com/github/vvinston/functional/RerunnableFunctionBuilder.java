package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

public final class RerunnableFunctionBuilder<T, R> {
    private final Function<T, R> function;

    RerunnableFunctionBuilder(@Nonnull final Function<T, R> function) {
        this.function = Objects.requireNonNull(function);
    }

    public Function<T, R> times(final int numberOfPossibleAttempts) {
        return new RerunnableFunction<>(function, numberOfPossibleAttempts);
    }
}
