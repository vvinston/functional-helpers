package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class GuardedBiConsumerBuilderStepOne<INPUT1, INPUT2> {

    private final BiConsumer<INPUT1, INPUT2> success;

    GuardedBiConsumerBuilderStepOne(@Nonnull final BiConsumer<INPUT1, INPUT2> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedBiConsumerBuilderStepTwo<INPUT1, INPUT2> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedBiConsumerBuilderStepTwo<>(clazz, Objects.requireNonNull(success));
    }
}
