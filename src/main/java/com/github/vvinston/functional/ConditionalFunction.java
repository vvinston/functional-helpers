package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunction<T, R> implements Function<T, R> {

    private final Predicate<T> predicate;
    private final Function<T, R> success;
    private final Function<T, R> fail;

    public ConditionalFunction(
            @Nonnull final Predicate<T> predicate,
            @Nonnull final Function<T, R> success,
            @Nonnull final Function<T, R> fail) {
        this.predicate = Objects.requireNonNull(predicate);
        this.success = Objects.requireNonNull(success);
        this.fail = Objects.requireNonNull(fail);
    }

    @Override
    public R apply(final T input) {
        return predicate.test(input) ? success.apply(input) : fail.apply(input);
    }

    public static <T> ConditionalFunctionBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return new ConditionalFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalFunctionBuilderStepOne<T> {

        private final Predicate<T> predicate;

        public ConditionalFunctionBuilderStepOne(@Nonnull final Predicate<T> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public <R> ConditionalFunctionBuilderStepTwo<T, R> then(@Nonnull final Function<T, R> success) {
            return new ConditionalFunctionBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
        }
    }

    public static final class ConditionalFunctionBuilderStepTwo<T, R> {

        private final Predicate<T> predicate;
        private final Function<T, R> success;

        private ConditionalFunctionBuilderStepTwo(@Nonnull final Predicate<T> predicate, @Nonnull final Function<T, R> success) {
            this.predicate = Objects.requireNonNull(predicate);
            this.success = Objects.requireNonNull(success);
        }

        public ConditionalFunction<T, R> otherwise(@Nonnull final Function<T, R> fail) {
            return new ConditionalFunction<>(predicate, success, Objects.requireNonNull(fail));
        }
    }
}
