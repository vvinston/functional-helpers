package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

final class EitherFunction<INPUT, RESULT1, RESULT2> implements Function<INPUT, Either<RESULT1, RESULT2>> {

    private final Predicate<INPUT> predicate;
    private final Function<INPUT, RESULT1> leftFunction;
    private final Function<INPUT, RESULT2> rightFunction;

    EitherFunction(
            @Nonnull final Predicate<INPUT> predicate,
            @Nonnull final Function<INPUT, RESULT1> leftFunction,
            @Nonnull final Function<INPUT, RESULT2> rightFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
        this.rightFunction = Objects.requireNonNull(rightFunction);
    }

    @Override
    public Either<RESULT1, RESULT2> apply(@Nullable final INPUT input) {
        return predicate.test(input)
                ? Either.left(leftFunction.apply(input))
                : Either.right(rightFunction.apply(input));
    }
}
