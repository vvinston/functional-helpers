package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public final class GuardedBiFunctionBuilderStepOne<T, U, R> {
    private final BiFunction<T, U, R> success;

    GuardedBiFunctionBuilderStepOne(@Nonnull final BiFunction<T, U, R> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedBiFunctionBuilderStepTwo<T, U, R> inCase(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedBiFunctionBuilderStepTwo<>(success, clazz);
    }
}
