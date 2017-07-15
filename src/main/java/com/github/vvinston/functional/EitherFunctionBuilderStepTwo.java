package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;

public final class EitherFunctionBuilderStepTwo<INPUT, RESULT1> {
    private final Predicate<INPUT> predicate;
    private final Function<INPUT, RESULT1> leftFunction;

    EitherFunctionBuilderStepTwo(@Nonnull final Predicate<INPUT> predicate, @Nonnull final Function<INPUT, RESULT1> leftFunction) {
        this.predicate = predicate;
        this.leftFunction = leftFunction;
    }

    public <RESULT2> Function<INPUT, Either<RESULT1, RESULT2>> otherwise(@Nonnull final Function<INPUT, RESULT2> rightFunction) {
        return new EitherFunction<>(predicate, leftFunction, rightFunction);
    }
}
