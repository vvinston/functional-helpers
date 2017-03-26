package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class GuardedBiConsumerBuilderStepTwo<T, U> {

    private final BiConsumer<T, U> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedBiConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final BiConsumer<T, U> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public BiConsumer<T, U> fallbackTo(@Nonnull final BiConsumer<T, U> fallback) {
        return new GuardedBiConsumer<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
