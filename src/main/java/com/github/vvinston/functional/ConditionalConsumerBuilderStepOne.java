package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepOne<T> {

    private final Predicate<T> predicate;

    ConditionalConsumerBuilderStepOne(@Nonnull final Predicate<T> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalConsumerBuilderStepTwo<T> then(@Nonnull final Consumer<T> success) {
        final List<Tuple<Predicate<T>, Consumer<T>>> cases = new LinkedList<>();
        cases.add(Tuple.of(predicate, success));
        return new ConditionalConsumerBuilderStepTwo<>(cases);
    }
}
