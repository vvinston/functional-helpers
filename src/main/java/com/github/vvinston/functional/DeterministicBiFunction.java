package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;

public final class DeterministicBiFunction<INPUT1, INPUT2, RESULT> implements BiFunction<INPUT1, INPUT2, RESULT> {
    private final Cache<Tuple<INPUT1, INPUT2>, RESULT> cache;
    private final BiFunction<INPUT1, INPUT2, RESULT> function;

    public DeterministicBiFunction(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function, @Nonnull final Cache<Tuple<INPUT1, INPUT2>, RESULT> cache) {
        this.cache = Objects.requireNonNull(cache);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public RESULT apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        final Tuple<INPUT1, INPUT2> key = SimpleTuple.of(input1, input2);

        if (!cache.exists(key)) {
            cache.put(key, function.apply(input1, input2));
        }

        return cache.get(key);
    }
}
