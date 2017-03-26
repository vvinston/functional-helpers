package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

final class GuardedBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final Class<? extends RuntimeException> clazz;
    private final BiFunction<T, U, R> success;
    private final BiFunction<T, U, R> fallback;

    GuardedBiFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiFunction<T, U, R> success,
            @Nonnull final BiFunction<T, U, R> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input1, final U input2) {
        try {
            return success.apply(input1, input2);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input1, input2);
        }
    }
}
