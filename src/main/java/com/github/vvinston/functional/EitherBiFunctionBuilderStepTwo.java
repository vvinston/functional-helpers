package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class EitherBiFunctionBuilderStepTwo<T, U, R1> {

    private final BiPredicate<T, U> predicate;
    private final BiFunction<T, U, R1> leftFunction;

    EitherBiFunctionBuilderStepTwo(@Nonnull final BiPredicate<T, U> predicate, @Nonnull final BiFunction<T, U, R1> leftFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
    }

    public <R2> BiFunction<T, U, Either<R1, R2>> otherwise(@Nonnull final BiFunction<T, U, R2> rightFunction) {
        return new EitherBiFunction<>(predicate, leftFunction, Objects.requireNonNull(rightFunction));
    }
}
