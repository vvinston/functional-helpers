package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class EitherBiFunction<T, U, R1, R2> implements BiFunction<T, U, Either<R1, R2>> {

    private final BiPredicate<T, U> predicate;
    private final BiFunction<T, U, R1> leftFunction;
    private final BiFunction<T, U, R2> rightFunction;

    public EitherBiFunction(
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

    public static <T, U> EitherBiFunctionBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new EitherBiFunctionBuilderStepOne<T, U>(Objects.requireNonNull(predicate));
    }

    public static final class EitherBiFunctionBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public EitherBiFunctionBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = predicate;
        }

        public <R1> EitherBiFunctionBuilderStepTwo<T, U, R1> then(@Nonnull final BiFunction<T, U, R1> success) {
            return new EitherBiFunctionBuilderStepTwo<T, U, R1>(predicate, Objects.requireNonNull(success));
        }
    }

    public static final class EitherBiFunctionBuilderStepTwo<T, U, R1> {

        private final BiPredicate<T, U> predicate;
        private final BiFunction<T, U, R1> leftFunction;

        public EitherBiFunctionBuilderStepTwo(@Nonnull final BiPredicate<T, U> predicate, @Nonnull final BiFunction<T, U, R1> leftFunction) {
            this.predicate = Objects.requireNonNull(predicate);
            this.leftFunction = Objects.requireNonNull(leftFunction);
        }

        public <R2> EitherBiFunction<T, U, R1, R2> otherwise(@Nonnull final BiFunction<T, U, R2> rightFunction) {
            return new EitherBiFunction<>(predicate, leftFunction, Objects.requireNonNull(rightFunction));
        }
    }
}
