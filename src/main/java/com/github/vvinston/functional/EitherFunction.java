package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

final class EitherFunction<T, R1, R2> implements Function<T, Either<R1, R2>> {

    private final Predicate<T> predicate;
    private final Function<T, R1> leftFunction;
    private final Function<T, R2> rightFunction;

    EitherFunction(
            @Nonnull final Predicate<T> predicate,
            @Nonnull final Function<T, R1> leftFunction,
            @Nonnull final Function<T, R2> rightFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
        this.rightFunction = Objects.requireNonNull(rightFunction);
    }

    @Override
    public Either<R1, R2> apply(final T input) {
        return predicate.test(input)
                ? Either.left(leftFunction.apply(input))
                : Either.right(rightFunction.apply(input));
    }
}
