package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class ConditionalBiConsumer<T, U> implements BiConsumer<T, U> {

    private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases = new LinkedList<>();
    private final BiConsumer<T, U> otherwise;

    public ConditionalBiConsumer(
            @Nonnull final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases,
            @Nonnull final BiConsumer<T, U> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nullable final T input1, @Nullable final U input2) {
        for (final Tuple<BiPredicate<T, U>, BiConsumer<T, U>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                kase.getSecond().accept(input1, input2);
                return;
            }
        }

        otherwise.accept(input1, input2);
    }

    public static <T, U> ConditionalBiConsumerBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalBiConsumerBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public ConditionalBiConsumerBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalBiConsumerBuilderStepTwo<T, U> then(@Nonnull final BiConsumer<T, U> success) {
            final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases = new LinkedList<>();
            cases.add(Tuple.of(predicate, success));
            return new ConditionalBiConsumerBuilderStepTwo<>(cases);
        }
    }

    public static final class ConditionalBiConsumerBuilderStepTwo<T, U> {

        private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases;

        private ConditionalBiConsumerBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases) {
            this.cases = Objects.requireNonNull(cases);
        }

        public ConditionalBiConsumerBuilderStepThree<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
            return new ConditionalBiConsumerBuilderStepThree<>(predicate, cases);
        }

        public ConditionalBiConsumer<T, U> otherwise(@Nonnull final BiConsumer<T, U> otherwise) {
            return new ConditionalBiConsumer<>(cases, Objects.requireNonNull(otherwise));
        }
    }

    public static final class ConditionalBiConsumerBuilderStepThree<T, U> {

        private final BiPredicate<T, U> predicate;
        private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases;

        public ConditionalBiConsumerBuilderStepThree(
                @Nonnull final BiPredicate<T, U> predicate,
                @Nonnull final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases) {
            this.predicate = predicate;
            this.cases = cases;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalBiConsumerBuilderStepTwo<T, U> then(@Nonnull final BiConsumer<T, U> success) {
            cases.add(Tuple.of(predicate, success));
            return new ConditionalBiConsumerBuilderStepTwo<>(cases);
        }
    }
}
