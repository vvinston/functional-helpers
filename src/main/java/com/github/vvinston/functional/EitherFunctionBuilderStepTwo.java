package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class EitherFunctionBuilderStepTwo<T, R1> {

    private final Predicate<T> predicate;
    private final Function<T, R1> leftFunction;

    EitherFunctionBuilderStepTwo(@Nonnull final Predicate<T> predicate, @Nonnull final Function<T, R1> leftFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
    }

    public <R2> Function<T, Either<R1, R2>> otherwise(@Nonnull final Function<T, R2> rightFunction) {
        return new EitherFunction<>(predicate, leftFunction, Objects.requireNonNull(rightFunction));
    }
}
