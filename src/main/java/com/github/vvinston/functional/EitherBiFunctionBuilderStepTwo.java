package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class EitherBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT1> {

    private final BiPredicate<INPUT1, INPUT2> predicate;
    private final BiFunction<INPUT1, INPUT2, RESULT1> leftFunction;

    EitherBiFunctionBuilderStepTwo(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate, @Nonnull final BiFunction<INPUT1, INPUT2, RESULT1> leftFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
    }

    public <RESULT2> BiFunction<INPUT1, INPUT2, Either<RESULT1, RESULT2>> otherwise(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT2> rightFunction) {
        return new EitherBiFunction<>(predicate, leftFunction, Objects.requireNonNull(rightFunction));
    }
}
