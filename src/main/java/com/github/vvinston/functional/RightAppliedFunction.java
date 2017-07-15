package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class RightAppliedFunction<INPUT1, INPUT2, RESULT> implements Function<INPUT1, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> biFunction;
    private final INPUT2 boundParameter;

    public RightAppliedFunction(
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> biFunction, @Nullable final INPUT2 boundParameter) {
        this.biFunction = Objects.requireNonNull(biFunction);
        this.boundParameter = boundParameter;
    }

    @Override
    public RESULT apply(@Nullable final INPUT1 input) {
        return biFunction.apply(input, boundParameter);
    }
}
