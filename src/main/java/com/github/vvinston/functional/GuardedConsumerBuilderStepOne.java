package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class GuardedConsumerBuilderStepOne<INPUT> {
    private final Consumer<INPUT> success;

    GuardedConsumerBuilderStepOne(@Nonnull final Consumer<INPUT> success) {
        this.success = success;
    }

    public GuardedConsumerBuilderStepTwo<INPUT> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedConsumerBuilderStepTwo<>(clazz, success);
    }
}
