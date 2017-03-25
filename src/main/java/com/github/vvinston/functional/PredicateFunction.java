package com.github.vvinston.functional;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateFunction<T> implements Function<T, Boolean> {

    private final Predicate<T> predicate;

    public PredicateFunction(final Predicate<T> predicate) {
        this.predicate = Objects.requireNonNull(predicate, "Predicate must not be null");
    }

    @Override
    public Boolean apply(final T input) {
        return predicate.test(input);
    }
}
