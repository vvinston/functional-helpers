package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

public final class NullableBiFunction<INPUT1, INPUT2, RESULT> implements BiFunction<INPUT1, INPUT2, RESULT> {

    private final BiFunction<INPUT1, INPUT2, Optional<RESULT>> success;
    private final BiFunction<INPUT1, INPUT2, RESULT> fallback;

    public NullableBiFunction(
            @Nonnull final BiFunction<INPUT1, INPUT2, Optional<RESULT>> success,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public RESULT apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        final Optional<RESULT> result = success.apply(input1, input2);
        return result.isPresent() ? result.get() : fallback.apply(input1, input2);
    }
}
