package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class FunctionBiPredicate<T, U> implements BiPredicate<T, U> {

    private final BiFunction<T, U, Boolean> function;

    FunctionBiPredicate(@Nonnull final BiFunction<T, U, Boolean> function) {
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public boolean test(final T input1, final U input2) {
        return function.apply(input1, input2);
    }
}
