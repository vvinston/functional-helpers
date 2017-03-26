package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

final class NullableFunction<T, R> implements Function<T, R> {

    private final Function<T, Optional<R>> success;
    private final Function<T, R> fallback;

    NullableFunction(@Nonnull final Function<T, Optional<R>> success, @Nonnull final Function<T, R> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public R apply(final T input) {
        final Optional<R> result = success.apply(input);
        return result.isPresent() ? result.get() : fallback.apply(input);
    }
}
