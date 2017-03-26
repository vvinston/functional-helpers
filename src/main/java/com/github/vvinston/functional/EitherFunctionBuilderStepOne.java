package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class EitherFunctionBuilderStepOne<T> {

    private final Predicate<T> predicate;

    public EitherFunctionBuilderStepOne(@Nonnull final Predicate<T> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public <R1> EitherFunctionBuilderStepTwo<T, R1> then(@Nonnull final Function<T, R1> success) {
        return new EitherFunctionBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
    }
}
