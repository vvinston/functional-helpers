package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class GuardedConsumerBuilderStepTwo<INPUT> {

    private final Consumer<INPUT> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Consumer<INPUT> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public Consumer<INPUT> fallbackTo(@Nonnull final Consumer<INPUT> fallback) {
        return new GuardedConsumer<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
