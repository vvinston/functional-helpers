package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public final class GuardedBiFunctionBuilderStepTwo<T, U, R> {
    private final BiFunction<T, U, R> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedBiFunctionBuilderStepTwo(@Nonnull final BiFunction<T, U, R> success, @Nonnull final Class<? extends RuntimeException> clazz) {
        this.success = Objects.requireNonNull(success);
        this.clazz = Objects.requireNonNull(clazz);
    }

    public BiFunction<T, U, R> fallbackTo(@Nonnull final BiFunction<T, U, R> fallback) {
        return new GuardedBiFunction<>(clazz, success, fallback);
    }
}
