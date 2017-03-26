package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepOne<T> {

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
