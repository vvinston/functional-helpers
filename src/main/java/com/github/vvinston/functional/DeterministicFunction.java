package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;

public final class DeterministicFunction<INPUT, RESULT> implements Function<INPUT, RESULT> {
    private final Cache<INPUT, RESULT> cache;
    private final Function<INPUT, RESULT> function;

    public DeterministicFunction(@Nonnull final Function<INPUT, RESULT> function, @Nonnull final Cache<INPUT, RESULT> cache) {
        this.cache = Objects.requireNonNull(cache);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public RESULT apply(@Nullable final INPUT input) {
        if (!cache.exists(input)) {
            cache.put(input, function.apply(input));
        }

        return cache.get(input);
    }
}

