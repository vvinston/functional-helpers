package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class GuardedBiConsumerBuilderStepOne<T, U> {

    private final BiConsumer<T, U> success;

    GuardedBiConsumerBuilderStepOne(@Nonnull final BiConsumer<T, U> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedBiConsumerBuilderStepTwo<T, U> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedBiConsumerBuilderStepTwo<>(clazz, Objects.requireNonNull(success));
    }
}
