package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

final class DeterministicFunction<T, R> implements Function<T, R> {

    private final Cache<T, R> cache;
    private final Function<T, R> function;

    DeterministicFunction(@Nonnull final Cache<T, R> cache, @Nonnull final Function<T, R> function) {
        this.cache = Objects.requireNonNull(cache);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public R apply(final T input) {
        if (!cache.exists(input)) {
            cache.put(input, function.apply(input));
        }

        return cache.get(input);
    }
}

