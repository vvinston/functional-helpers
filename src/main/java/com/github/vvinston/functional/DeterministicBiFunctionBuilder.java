package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class DeterministicBiFunctionBuilder {
    public <INPUT1, INPUT2, RESULT> BiFunction<INPUT1, INPUT2, RESULT> deterministic(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function) {
        return deterministic(function, () -> new HashMap<>());
    }

    public <INPUT1, INPUT2, RESULT> BiFunction<INPUT1, INPUT2, RESULT> deterministic(
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function,
            @Nonnull final Supplier<Map<Tuple<INPUT1, INPUT2>, RESULT>> supplier) {
        return deterministic(function, new MapCache<>(supplier.get()));
    }

    public <INPUT1, INPUT2, RESULT> BiFunction<INPUT1, INPUT2, RESULT> deterministic(
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function,
            @Nonnull final Cache<Tuple<INPUT1, INPUT2>, RESULT> cache) {
        return new DeterministicBiFunction<>(function, cache);
    }
}
