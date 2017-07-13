package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class GuardedConsumerBuilderStepOne<INPUT> {

    private final Consumer<INPUT> success;

    GuardedConsumerBuilderStepOne(@Nonnull final Consumer<INPUT> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedConsumerBuilderStepTwo<INPUT> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedConsumerBuilderStepTwo<>(clazz, Objects.requireNonNull(success));
    }
}
