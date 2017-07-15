package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class LeftAppliedFunction<INPUT1, INPUT2, RESULT> implements Function<INPUT2, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> biFunction;
    private final INPUT1 boundParameter;

    public LeftAppliedFunction(
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> biFunction, @Nullable final INPUT1 boundParameter) {
        this.biFunction = Objects.requireNonNull(biFunction);
        this.boundParameter = boundParameter;
    }

    @Override
    public RESULT apply(@Nullable final INPUT2 input) {
        return biFunction.apply(boundParameter, input);
    }
}
