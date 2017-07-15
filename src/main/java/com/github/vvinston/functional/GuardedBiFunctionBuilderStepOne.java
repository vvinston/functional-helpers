package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;

public final class GuardedBiFunctionBuilderStepOne<INPUT1, INPUT2, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> success;

    GuardedBiFunctionBuilderStepOne(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success) {
        this.success = success;
    }

    public GuardedBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT> inCase(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedBiFunctionBuilderStepTwo<>(success, clazz);
    }
}
