package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public final class GuardedBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedBiFunctionBuilderStepTwo(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success, @Nonnull final Class<? extends RuntimeException> clazz) {
        this.success = Objects.requireNonNull(success);
        this.clazz = Objects.requireNonNull(clazz);
    }

    public BiFunction<INPUT1, INPUT2, RESULT> fallbackTo(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> fallback) {
        return new GuardedBiFunction<>(clazz, success, fallback);
    }
}
