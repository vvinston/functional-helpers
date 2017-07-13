package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class EitherFunctionBuilderStepOne<INPUT> {

    private final Predicate<INPUT> predicate;

    public EitherFunctionBuilderStepOne(@Nonnull final Predicate<INPUT> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public <RESULT1> EitherFunctionBuilderStepTwo<INPUT, RESULT1> then(@Nonnull final Function<INPUT, RESULT1> success) {
        return new EitherFunctionBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
    }
}
