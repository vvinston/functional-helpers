package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

final class MemoicFunction<T, R> implements Function<T, R> {

    private final Map<T, R> map;
    private final Function<T, R> function;

    MemoicFunction(@Nonnull final Map<T, R> map, @Nonnull final Function<T, R> function) {
        this.map = Objects.requireNonNull(map);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public R apply(final T t) {
        return map.computeIfAbsent(t, function);
    }
}
