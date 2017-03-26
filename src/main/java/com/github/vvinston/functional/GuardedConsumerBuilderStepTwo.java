package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class GuardedConsumerBuilderStepTwo<T> {

    private final Consumer<T> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Consumer<T> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public Consumer<T> fallbackTo(@Nonnull final Consumer<T> fallback) {
        return new GuardedConsumer<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
