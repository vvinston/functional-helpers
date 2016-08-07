package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.function.Function;

public class DeterministicFunction<T, R> implements Function<T, R> {

    private final Cache<T, R> cache;
    private final Function<T, R> function;

    public DeterministicFunction(@Nonnull final Cache<T, R> cache, @Nonnull final Function<T, R> function) {
        this.cache = Preconditions.checkNotNull(cache);
        this.function = Preconditions.checkNotNull(function);
    }

    @Override
    public R apply(final T input) {
        if (!cache.exists(input)) {
            cache.put(input, function.apply(input));
        }

        return cache.get(input);
    }

    public static <T, R> DeterministicFunction<T, R> getMapCachedFunction(@Nonnull final Function<T, R> function) {
        return new DeterministicFunction<>(new MapCache<>(new HashMap<>()), Preconditions.checkNotNull(function));
    }
}

