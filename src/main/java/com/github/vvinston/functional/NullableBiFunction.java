package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.BiFunction;

public class NullableBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final BiFunction<T, U, Optional<R>> success;
    private final BiFunction<T, U, R> fallback;

    public NullableBiFunction(
            @Nonnull final BiFunction<T, U, Optional<R>> success,
            @Nonnull final BiFunction<T, U, R> fallback) {
        this.success = success;
        this.fallback = fallback;
    }

    @Override
    public R apply(final T input1, final U input2) {
        final Optional<R> result = success.apply(input1, input2);
        return result.isPresent() ? result.get() : fallback.apply(input1, input2);
    }

    public static <T, U, R> NullableBiFunction<T, U, R> getFunctionWithConstantFallbackValue(
            @Nonnull final BiFunction<T, U, Optional<R>> success,
            final R fallbackValue) {
        final BiFunction<T, U, R> fallback =  (input1, input2)  -> fallbackValue;
        return new NullableBiFunction<>(Preconditions.checkNotNull(success), fallback);
    }
}
