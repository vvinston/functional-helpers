package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepThree<T, U, R> {

    private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases;
    private final BiPredicate<T, U> predicate;

    ConditionalBiFunctionBuilderStepThree(
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
