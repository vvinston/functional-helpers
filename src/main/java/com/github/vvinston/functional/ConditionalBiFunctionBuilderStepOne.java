package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepOne<T, U> {

    private final BiPredicate<T, U> predicate;

    ConditionalBiFunctionBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public <R> ConditionalBiFunctionBuilderStepTwo<T, U, R> then(@Nonnull final BiFunction<T, U, R> success) {
        final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases = new LinkedList<>();
        cases.add(Tuple.of(predicate, Objects.requireNonNull(success)));
        return new ConditionalBiFunctionBuilderStepTwo<>(cases);
    }
}
