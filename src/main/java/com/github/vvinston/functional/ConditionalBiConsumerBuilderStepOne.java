package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepOne<T, U> {

    private final BiPredicate<T, U> predicate;

    ConditionalBiConsumerBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalBiConsumerBuilderStepTwo<T, U> then(@Nonnull final BiConsumer<T, U> success) {
        final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases = new LinkedList<>();
        cases.add(Tuple.of(predicate, success));
        return new ConditionalBiConsumerBuilderStepTwo<>(cases);
    }
}
