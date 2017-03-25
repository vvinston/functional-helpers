package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class ConditionalBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases = new LinkedList<>();
    private final BiFunction<T, U, R> otherwise;

    public ConditionalBiFunction(
            @Nonnull final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases,
            @Nonnull final BiFunction<T, U, R> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public R apply(@Nonnull final T input1, @Nonnull final U input2) {
        for (final Tuple<BiPredicate<T, U>, BiFunction<T, U, R>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                return kase.getSecond().apply(input1, input2);
            }
        }

        return  otherwise.apply(input1, input2);
    }

    public static <T, U> ConditionalBiFunctionBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalBiFunctionBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public ConditionalBiFunctionBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public <R> ConditionalBiFunctionBuilderStepTwo<T, U, R> then(@Nonnull final BiFunction<T, U, R> success) {
            final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases = new LinkedList<>();
            cases.add(Tuple.of(predicate, Objects.requireNonNull(success)));
            return new ConditionalBiFunctionBuilderStepTwo<>(cases);
        }
    }

    public static final class ConditionalBiFunctionBuilderStepTwo<T, U, R> {

        private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases;

        private ConditionalBiFunctionBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases) {
            this.cases = Objects.requireNonNull(cases);
        }

        public ConditionalBiFunctionBuilderStepThree<T, U, R> when(@Nonnull final BiPredicate<T, U> predicate) {
            return new ConditionalBiFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
        }

        public ConditionalBiFunction<T, U, R> otherwise(@Nonnull final BiFunction<T, U, R> otherwise) {
            return new ConditionalBiFunction<>(cases, Objects.requireNonNull(otherwise));
        }
    }

    public static final class ConditionalBiFunctionBuilderStepThree<T, U, R> {

        private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases;
        private final BiPredicate<T, U> predicate;

        public ConditionalBiFunctionBuilderStepThree(
                @Nonnull final BiPredicate<T, U> predicate,
                @Nonnull final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases) {
            this.predicate = Objects.requireNonNull(predicate);
            this.cases = Objects.requireNonNull(cases);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalBiFunctionBuilderStepTwo<T, U, R> then(@Nonnull final BiFunction<T, U, R> success) {
            cases.add(Tuple.of(predicate, Objects.requireNonNull(success)));
            return new ConditionalBiFunctionBuilderStepTwo<>(cases);
        }
    }
}
