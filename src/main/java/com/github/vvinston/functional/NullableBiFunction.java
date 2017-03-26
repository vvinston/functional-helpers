package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

final class NullableBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final BiFunction<T, U, Optional<R>> success;
    private final BiFunction<T, U, R> fallback;

    NullableBiFunction(
            @Nonnull final BiFunction<T, U, Optional<R>> success,
            @Nonnull final BiFunction<T, U, R> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public R apply(final T input1, final U input2) {
        final Optional<R> result = success.apply(input1, input2);
        return result.isPresent() ? result.get() : fallback.apply(input1, input2);
    }
}
