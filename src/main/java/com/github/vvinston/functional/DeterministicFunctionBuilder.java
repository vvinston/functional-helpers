package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DeterministicFunctionBuilder {
    public <INPUT, RESULT> Function<INPUT, RESULT> deterministic(@Nonnull final Function<INPUT, RESULT> function) {
        return deterministic(function, HashMap::new);
    }

    public <INPUT, RESULT> Function<INPUT, RESULT> deterministic(
            @Nonnull final Function<INPUT, RESULT> function, @Nonnull final Supplier<Map<INPUT, RESULT>> supplier) {
        return deterministic(function, new MapCache<>(supplier.get()));
    }

    public <INPUT, RESULT> Function<INPUT, RESULT> deterministic(
            @Nonnull final Function<INPUT, RESULT> function, @Nonnull final Cache<INPUT, RESULT> cache) {
        return new DeterministicFunction<>(function, cache);
    }
}
