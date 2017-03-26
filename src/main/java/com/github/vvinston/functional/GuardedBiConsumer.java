package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

final class GuardedBiConsumer<T, U> implements BiConsumer<T, U> {

    private final Class<? extends RuntimeException> clazz;
    private final BiConsumer<T, U> success;
    private final BiConsumer<T, U> fallback;

    GuardedBiConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiConsumer<T, U> success,
            @Nonnull final BiConsumer<T, U> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input1, final U input2) {
        try {
            success.accept(input1, input2);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input1, input2);
        }
    }
}
