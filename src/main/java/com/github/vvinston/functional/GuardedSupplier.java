package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

final class GuardedSupplier<T> implements Supplier<T> {

    private final Class<? extends RuntimeException> clazz;
    private final Supplier<T> success;
    private final Supplier<T> fallback;

    GuardedSupplier(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Supplier<T> success,
            @Nonnull final Supplier<T> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public T get() {
        try {
            return success.get();
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.get();
        }
    }
}
