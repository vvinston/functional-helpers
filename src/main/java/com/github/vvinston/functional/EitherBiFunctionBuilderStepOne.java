package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class EitherBiFunctionBuilderStepOne<T, U> {

    private final BiPredicate<T, U> predicate;

    EitherBiFunctionBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
        this.predicate = predicate;
    }

    public <R1> EitherBiFunctionBuilderStepTwo<T, U, R1> then(@Nonnull final BiFunction<T, U, R1> success) {
        return new EitherBiFunctionBuilderStepTwo<T, U, R1>(predicate, Objects.requireNonNull(success));
    }
}
