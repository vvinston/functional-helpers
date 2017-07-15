package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class EitherBiFunctionBuilderStepOne<INPUT1, INPUT2> {
    private final BiPredicate<INPUT1, INPUT2> predicate;

    EitherBiFunctionBuilderStepOne(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        this.predicate = predicate;
    }

    public <RESULT1> EitherBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT1> then(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT1> success) {
        return new EitherBiFunctionBuilderStepTwo<>(predicate, success);
    }
}
