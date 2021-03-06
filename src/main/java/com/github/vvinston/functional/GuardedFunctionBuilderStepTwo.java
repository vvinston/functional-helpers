package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class GuardedFunctionBuilderStepTwo<INPUT, RESULT> {
    private final Function<INPUT, RESULT> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedFunctionBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Function<INPUT, RESULT> success) {
        this.clazz = clazz;
        this.success = success;
    }

    public Function<INPUT, RESULT> fallbackTo(@Nonnull final Function<INPUT, RESULT> fallback) {
        return new GuardedFunction<>(clazz, success, fallback);
    }
}
