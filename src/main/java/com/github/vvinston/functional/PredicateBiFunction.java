package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class PredicateBiFunction<T, U> implements BiFunction<T, U, Boolean> {

    private final BiPredicate<T, U> predicate;

    PredicateBiFunction(@Nonnull final BiPredicate<T, U> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @Override
    public Boolean apply(@Nonnull final T input1, @Nonnull final U input2) {
        return predicate.test(input1, input2);
    }
}
