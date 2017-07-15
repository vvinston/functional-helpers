package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Function;

public final class NullableFunctionBuilder {
    public <INPUT, RESULT> Function<INPUT, RESULT> nullableWithFallbackValue(
            @Nonnull final Function<INPUT, Optional<RESULT>> success, final RESULT fallbackValue) {
        return nullableWithFallback(success, input -> fallbackValue);
    }

    public <INPUT, RESULT> Function<INPUT, RESULT> nullableWithFallback(
            @Nonnull final Function<INPUT, Optional<RESULT>> success, @Nonnull final Function<INPUT, RESULT> fallback) {
        return new NullableFunction<>(success, fallback);
    }
}
