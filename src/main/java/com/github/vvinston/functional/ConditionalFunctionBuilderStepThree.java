package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepThree<T, R> {

    private final List<Tuple<Predicate<T>, Function<T, R>>> cases;
    private final Predicate<T> predicate;

    ConditionalFunctionBuilderStepThree(
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
