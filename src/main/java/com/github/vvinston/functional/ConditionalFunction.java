package com.github.vvinston.functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunction<T, R> implements Function<T, R> {

    private final Predicate<T> predicate;

    private final Function<T, R> success;

    private final Function<T, R> fail;

    public ConditionalFunction(final Predicate<T> predicate, final Function<T, R> success, final Function<T, R> fail) {
        this.predicate = predicate;
        this.success = success;
        this.fail = fail;
    }

    @Override
    public R apply(final T input) {
        return predicate.test(input) ? success.apply(input) : fail.apply(input);
    }

    public static <T> ConditionalFunctionBuilderStepOne<T> when (final Predicate<T> predicate) {
        return new ConditionalFunctionBuilderStepOne<>(predicate);
    }

    public static final class ConditionalFunctionBuilderStepOne<T> {

        private final Predicate<T> predicate;

        public ConditionalFunctionBuilderStepOne(final Predicate<T> predicate) {
            this.predicate = predicate;
        }

        public <R> ConditionalFunctionBuilderStepTwo<T, R> then(final Function<T, R> success) {
            return new ConditionalFunctionBuilderStepTwo<>(predicate, success);
        }
    }

    public static final class ConditionalFunctionBuilderStepTwo<T, R> {

        private final Predicate<T> predicate;

        private final Function<T, R> success;

        private ConditionalFunctionBuilderStepTwo(final Predicate<T> predicate, final Function<T, R> success) {
            this.predicate = predicate;
            this.success = success;
        }

        public ConditionalFunction<T, R> otherwise(final Function<T, R> fail) {
            return new ConditionalFunction<>(predicate, success, fail);
        }
    }
}
