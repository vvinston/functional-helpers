package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class GuardedFunctionBuilderStepOne<INPUT, RESULT> {
    private final Function<INPUT, RESULT> success;

    GuardedFunctionBuilderStepOne(@Nonnull final Function<INPUT, RESULT> success) {
        this.success = success;
    }

    public GuardedFunctionBuilderStepTwo<INPUT, RESULT> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedFunctionBuilderStepTwo<>(clazz, success);
    }
}
