package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunction<T, R> implements Function<T, R> {

    private final List<Tuple<Predicate<T>, Function<T, R>>> cases = new LinkedList<>();
    private final Function<T, R> otherwise;

    public ConditionalFunction(
            @Nonnull final List<Tuple<Predicate<T>, Function<T, R>>> cases,
            @Nonnull final Function<T, R> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public R apply(final T input) {
        for (final Tuple<Predicate<T>, Function<T, R>> kase : cases) {
            if (kase.getFirst().test(input)) {
                return kase.getSecond().apply(input);
            }
        }

        return  otherwise.apply(input);
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
            final List<Tuple<Predicate<T>, Function<T, R>>> cases = new LinkedList<>();
            cases.add(Tuple.of(predicate, Objects.requireNonNull(success)));
            return new ConditionalFunctionBuilderStepTwo<>(cases);
        }
    }

    public static final class ConditionalFunctionBuilderStepTwo<T, R> {

        private final List<Tuple<Predicate<T>, Function<T, R>>> cases;

        private ConditionalFunctionBuilderStepTwo(@Nonnull final List<Tuple<Predicate<T>, Function<T, R>>> cases) {
            this.cases = Objects.requireNonNull(cases);
        }

        public ConditionalFunctionBuilderStepThree<T, R> when(@Nonnull final Predicate<T> predicate) {
            return new ConditionalFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
        }

        public ConditionalFunction<T, R> otherwise(@Nonnull final Function<T, R> otherwise) {
            return new ConditionalFunction<>(cases, Objects.requireNonNull(otherwise));
        }
    }

    public static final class ConditionalFunctionBuilderStepThree<T, R> {

        private final List<Tuple<Predicate<T>, Function<T, R>>> cases;
        private final Predicate<T> predicate;

        public ConditionalFunctionBuilderStepThree(
                @Nonnull final Predicate<T> predicate,
                @Nonnull final List<Tuple<Predicate<T>, Function<T, R>>> cases) {
            this.predicate = Objects.requireNonNull(predicate);
            this.cases = Objects.requireNonNull(cases);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalFunctionBuilderStepTwo<T, R> then(@Nonnull final Function<T, R> success) {
            cases.add(Tuple.of(predicate, Objects.requireNonNull(success)));
            return new ConditionalFunctionBuilderStepTwo<>(cases);
        }
    }
}
