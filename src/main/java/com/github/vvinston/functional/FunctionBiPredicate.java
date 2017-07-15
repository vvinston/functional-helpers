package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class FunctionBiPredicate<INPUT1, INPUT2> implements BiPredicate<INPUT1, INPUT2> {
    private final BiFunction<INPUT1, INPUT2, Boolean> function;

    public FunctionBiPredicate(@Nonnull final BiFunction<INPUT1, INPUT2, Boolean> function) {
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public boolean test(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        return function.apply(input1, input2);
    }
}
