package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

final class GuardedConsumer<T> implements Consumer<T> {

    private final Class<? extends RuntimeException> clazz;
    private final Consumer<T> success;
    private final Consumer<T> fallback;

    GuardedConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Consumer<T> success,
            @Nonnull final Consumer<T> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input) {
        try {
            success.accept(input);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input);
        }
    }
}
