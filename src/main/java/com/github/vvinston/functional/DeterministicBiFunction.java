package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public class DeterministicBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final Cache<Tuple<T, U>, R> cache;
    private final BiFunction<T, U, R> function;

    public DeterministicBiFunction(@Nonnull final Cache<Tuple<T, U>, R> cache, @Nonnull final BiFunction<T, U, R> function) {
        this.cache = Objects.requireNonNull(cache);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public R apply(final T input1, final U input2) {
        final Tuple<T, U> key = Tuple.of(input1, input2);

        if (!cache.exists(key)) {
            cache.put(key, function.apply(input1, input2));
        }

        return cache.get(key);
    }
}
