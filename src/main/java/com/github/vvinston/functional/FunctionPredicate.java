package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

final class FunctionPredicate<T> implements Predicate<T> {

    private final Function<T, Boolean> function;

    FunctionPredicate(@Nonnull final Function<T, Boolean> function) {
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public boolean test(final T input) {
        return function.apply(input);
    }
}
