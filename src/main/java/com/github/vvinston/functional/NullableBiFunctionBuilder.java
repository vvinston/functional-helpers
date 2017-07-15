package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.BiFunction;

public final class NullableBiFunctionBuilder {
    public <INPUT1, INPUT2, RESULT> BiFunction<INPUT1, INPUT2, RESULT> nullableWithFallbackValue(
            @Nonnull final BiFunction<INPUT1, INPUT2, Optional<RESULT>> success, final RESULT fallbackValue) {
        return nullableWithFallback(success, (input1, input2)  -> fallbackValue);
    }

    public <INPUT1, INPUT2, RESULT> BiFunction<INPUT1, INPUT2, RESULT> nullableWithFallback(
            @Nonnull final BiFunction<INPUT1, INPUT2, Optional<RESULT>> success, @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> fallback) {
        return new NullableBiFunction<>(success, fallback);
    }
}
