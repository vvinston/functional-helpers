package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class EitherFunction<T, R1, R2> implements Function<T, Either<R1, R2>> {

    private final Predicate<T> predicate;
    private final Function<T, R1> leftFunction;
    private final Function<T, R2> rightFunction;

    public EitherFunction(
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

    public static <T> EitherFunctionBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return new EitherFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class EitherFunctionBuilderStepOne<T> {

        private final Predicate<T> predicate;

        public EitherFunctionBuilderStepOne(@Nonnull final Predicate<T> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public <R1> EitherFunctionBuilderStepTwo<T, R1> then(@Nonnull final Function<T, R1> success) {
            return new EitherFunctionBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
        }
    }

    public static final class EitherFunctionBuilderStepTwo<T, R1> {

        private final Predicate<T> predicate;
        private final Function<T, R1> leftFunction;

        private EitherFunctionBuilderStepTwo(@Nonnull final Predicate<T> predicate, @Nonnull final Function<T, R1> leftFunction) {
            this.predicate = Objects.requireNonNull(predicate);
            this.leftFunction = Objects.requireNonNull(leftFunction);
        }

        public <R2> EitherFunction<T, R1, R2> otherwise(@Nonnull final Function<T, R2> rightFunction) {
            return new EitherFunction<>(predicate, leftFunction, Objects.requireNonNull(rightFunction));
        }
    }
}
