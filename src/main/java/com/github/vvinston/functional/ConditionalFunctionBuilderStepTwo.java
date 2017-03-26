package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepTwo<T, R> {

    private final List<Tuple<Predicate<T>, Function<T, R>>> cases;

    ConditionalFunctionBuilderStepTwo(@Nonnull final List<Tuple<Predicate<T>, Function<T, R>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalFunctionBuilderStepThree<T, R> when(@Nonnull final Predicate<T> predicate) {
        return new ConditionalFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
    }

    public Function<T, R> otherwise(@Nonnull final Function<T, R> otherwise) {
        return new ConditionalFunction<>(cases, Objects.requireNonNull(otherwise));
    }
}
