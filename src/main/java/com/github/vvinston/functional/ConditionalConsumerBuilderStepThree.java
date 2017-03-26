package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepThree<T> {

    private final Predicate<T> predicate;
    private final List<Tuple<Predicate<T>, Consumer<T>>> cases;

    ConditionalConsumerBuilderStepThree(
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
