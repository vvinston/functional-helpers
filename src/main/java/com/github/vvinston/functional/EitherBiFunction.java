package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class EitherBiFunction<INPUT1, INPUT2, RESUL1, RESULT2> implements BiFunction<INPUT1, INPUT2, Either<RESUL1, RESULT2>> {
    private final BiPredicate<INPUT1, INPUT2> predicate;
    private final BiFunction<INPUT1, INPUT2, RESUL1> leftFunction;
    private final BiFunction<INPUT1, INPUT2, RESULT2> rightFunction;

    public EitherBiFunction(
            @Nonnull final BiPredicate<INPUT1, INPUT2> predicate,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESUL1> leftFunction,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT2> rightFunction) {
        this.predicate = Objects.requireNonNull(predicate);
        this.leftFunction = Objects.requireNonNull(leftFunction);
        this.rightFunction = Objects.requireNonNull(rightFunction);
    }

    @Override
    public Either<RESUL1, RESULT2> apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        return predicate.test(input1, input2)
                ? Either.left(leftFunction.apply(input1, input2))
                : Either.right(rightFunction.apply(input1, input2));
    }
}
