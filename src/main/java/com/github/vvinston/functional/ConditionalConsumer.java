package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConditionalConsumer<T> implements Consumer<T> {

    private final List<Tuple<Predicate<T>, Consumer<T>>> cases = new LinkedList<>();
    private final Consumer<T> otherwise;

    public ConditionalConsumer(
            @Nonnull final List<Tuple<Predicate<T>, Consumer<T>>> cases,
            @Nonnull final Consumer<T> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nonnull final T input) {
        for (final Tuple<Predicate<T>, Consumer<T>> kase : cases) {
            if (kase.getFirst().test(input)) {
                kase.getSecond().accept(input);
                return;
            }
        }

        otherwise.accept(input);
    }

    public static <T> ConditionalConsumerBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return new ConditionalConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalConsumerBuilderStepOne<T> {

        private final Predicate<T> predicate;

        public ConditionalConsumerBuilderStepOne(@Nonnull final Predicate<T> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalConsumerBuilderStepTwo<T> then(@Nonnull final Consumer<T> success) {
            final List<Tuple<Predicate<T>, Consumer<T>>> cases = new LinkedList<>();
            cases.add(Tuple.of(predicate, success));
            return new ConditionalConsumerBuilderStepTwo<>(cases);
        }
    }

    public static final class ConditionalConsumerBuilderStepTwo<T> {

        private final List<Tuple<Predicate<T>, Consumer<T>>> cases;

        private ConditionalConsumerBuilderStepTwo(@Nonnull final List<Tuple<Predicate<T>, Consumer<T>>> cases) {
            this.cases = Objects.requireNonNull(cases);
        }

        public ConditionalConsumerBuilderStepThree<T> when(@Nonnull final Predicate<T> predicate) {
            return new ConditionalConsumerBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
        }

        public ConditionalConsumer<T> otherwise(@Nonnull final Consumer<T> otherwise) {
            return new ConditionalConsumer<>(cases, Objects.requireNonNull(otherwise));
        }
    }

    public static final class ConditionalConsumerBuilderStepThree<T> {

        private final Predicate<T> predicate;
        private final List<Tuple<Predicate<T>, Consumer<T>>> cases;

        public ConditionalConsumerBuilderStepThree(
                @Nonnull final Predicate<T> predicate,
                @Nonnull final List<Tuple<Predicate<T>, Consumer<T>>> cases) {
            this.cases = cases;
            this.predicate = predicate;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalConsumerBuilderStepTwo<T> then(@Nonnull final Consumer<T> success) {
            cases.add(Tuple.of(predicate, success));
            return new ConditionalConsumerBuilderStepTwo<>(cases);
        }
    }
}
