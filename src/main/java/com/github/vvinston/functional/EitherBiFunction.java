package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class EitherBiFunction<T, U, R1, R2> implements BiFunction<T, U, Either<R1, R2>> {

    private final BiPredicate<T, U> predicate;
    private final BiFunction<T, U, R1> leftFunction;
    private final BiFunction<T, U, R2> rightFunction;

    EitherBiFunction(
            @Nonnull final BiPredicate<T, U> predicate,
            @Nonnull final BiFunction<T, U, R1> leftFunction,
            @Nonnull final BiFunction<T, U, R2> rightFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
        this.rightFunction = Objects.requireNonNull(rightFunction);
    }

    @Override
    public Either<R1, R2> apply(final T input1, final U input2) {
        return predicate.test(input1, input2)
                ? Either.left(leftFunction.apply(input1, input2))
                : Either.right(rightFunction.apply(input1, input2));
    }
}
