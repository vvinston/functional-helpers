package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

public final class GuardedFunctionBuilderStepOne<T, R> {

    private final Function<T, R> success;

    GuardedFunctionBuilderStepOne(@Nonnull final Function<T, R> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedFunctionBuilderStepTwo<T, R> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedFunctionBuilderStepTwo<>(clazz, Objects.requireNonNull(success));
    }
}
