package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Function;

public class NullableFunction<T, R> implements Function<T, R> {

    private final Function<T, Optional<R>> success;
    private final Function<T, R> fallback;

    public NullableFunction(@Nonnull final Function<T, Optional<R>> success, @Nonnull final Function<T, R> fallback) {
        this.success = success;
        this.fallback = fallback;
    }

    @Override
    public R apply(final T input) {
        final Optional<R> result = success.apply(input);
        return result.isPresent() ? result.get() : fallback.apply(input);
    }

    public static <T, R> NullableFunction<T, R> getFunctionWithConstantFallbackValue(@Nonnull final Function<T, Optional<R>> success, final R fallbackValue) {
        final Function<T, R> fallback = input -> fallbackValue;
        return new NullableFunction<>(success, fallback);
    }
}
