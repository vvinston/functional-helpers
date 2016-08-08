package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class ConditionalBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final BiPredicate<T, U> predicate;
    private final BiFunction<T, U, R> success;
    private final BiFunction<T, U, R> fail;

    public ConditionalBiFunction(
            @Nonnull final BiPredicate<T, U> predicate,
            @Nonnull final BiFunction<T, U, R> success,
            @Nonnull final BiFunction<T, U, R> fail) {
        this.predicate = Preconditions.checkNotNull(predicate);
        this.success = Preconditions.checkNotNull(success);
        this.fail = Preconditions.checkNotNull(fail);
    }

    @Override
    public R apply(@Nonnull final T input1, @Nonnull final U input2) {
        return predicate.test(input1, input2) ? success.apply(input1, input2) : fail.apply(input1, input2);
    }

    public static <T, U> ConditionalBiFunctionBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiFunctionBuilderStepOne<>(Preconditions.checkNotNull(predicate));
    }

    public static final class ConditionalBiFunctionBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public ConditionalBiFunctionBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public <R> ConditionalBiFunctionBuilderStepTwo<T, U, R> then(@Nonnull final BiFunction<T, U, R> success) {
            return new ConditionalBiFunctionBuilderStepTwo<>(predicate, Preconditions.checkNotNull(success));
        }
    }

    public static final class ConditionalBiFunctionBuilderStepTwo<T, U, R> {

        private final BiPredicate<T, U> predicate;
        private final BiFunction<T, U, R> success;

        private ConditionalBiFunctionBuilderStepTwo(@Nonnull final BiPredicate<T, U> predicate, @Nonnull final BiFunction<T, U, R> success) {
            this.predicate = Preconditions.checkNotNull(predicate);
            this.success = Preconditions.checkNotNull(success);
        }

        public ConditionalBiFunction<T, U, R> otherwise(@Nonnull final BiFunction<T, U, R> fail) {
            return new ConditionalBiFunction<>(predicate, success, Preconditions.checkNotNull(fail));
        }
    }
}
