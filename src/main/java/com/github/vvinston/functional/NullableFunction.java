package com.github.vvinston.functional;

import java.util.Optional;
import java.util.function.Function;

public class NullableFunction<T, R> implements Function<T, R> {

    private final Function<T, Optional<R>> success;
    private final Function<T, R> fallback;

    public NullableFunction(final Function<T, Optional<R>> success, final Function<T, R> fallback) {
        this.success = success;
        this.fallback = fallback;
    }

    @Override
    public R apply(final T input) {
        final Optional<R> result = success.apply(input);
        return result.isPresent() ? result.get() : fallback.apply(input);
    }
}
