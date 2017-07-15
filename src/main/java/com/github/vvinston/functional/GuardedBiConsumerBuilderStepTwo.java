package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public final class GuardedBiConsumerBuilderStepTwo<INPUT1, INPUT2> {
    private final BiConsumer<INPUT1, INPUT2> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedBiConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final BiConsumer<INPUT1, INPUT2> success) {
        this.clazz = clazz;
        this.success = success;
    }

    public BiConsumer<INPUT1, INPUT2> fallbackTo(@Nonnull final BiConsumer<INPUT1, INPUT2> fallback) {
        return new GuardedBiConsumer<>(clazz, success, fallback);
    }
}
