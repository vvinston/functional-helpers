package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class GuardedConsumerBuilderStepOne<T> {

    private final Consumer<T> success;

    GuardedConsumerBuilderStepOne(@Nonnull final Consumer<T> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedConsumerBuilderStepTwo<T> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedConsumerBuilderStepTwo<>(clazz, Objects.requireNonNull(success));
    }
}
