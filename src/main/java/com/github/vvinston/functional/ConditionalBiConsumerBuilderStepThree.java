package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepThree<T, U> {

    private final BiPredicate<T, U> predicate;
    private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases;

    ConditionalBiConsumerBuilderStepThree(
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
