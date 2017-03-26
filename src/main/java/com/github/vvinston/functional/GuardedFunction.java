package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

final class GuardedFunction<T, R> implements Function<T, R> {

    private final Class<? extends RuntimeException> clazz;
    private final Function<T, R> success;
    private final Function<T, R> fallback;

    GuardedFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Function<T, R> success,
            @Nonnull final Function<T, R> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input) {
        try {
            return success.apply(input);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input);
        }
    }
}

