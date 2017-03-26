package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

public final class GuardedFunctionBuilderStepTwo<T, R> {

    private final Function<T, R> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedFunctionBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Function<T, R> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public Function<T, R> fallbackTo(@Nonnull final Function<T, R> fallback) {
        return new GuardedFunction<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
