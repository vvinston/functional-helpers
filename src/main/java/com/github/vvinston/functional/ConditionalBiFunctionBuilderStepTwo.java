package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepTwo<T, U, R> {

    private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases;

    ConditionalBiFunctionBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalBiFunctionBuilderStepThree<T, U, R> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
    }

    public BiFunction<T, U, R> otherwise(@Nonnull final BiFunction<T, U, R> otherwise) {
        return new ConditionalBiFunction<>(cases, Objects.requireNonNull(otherwise));
    }
}
