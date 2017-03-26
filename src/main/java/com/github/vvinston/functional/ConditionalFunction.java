package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

final class ConditionalFunction<T, R> implements Function<T, R> {

    private final List<Tuple<Predicate<T>, Function<T, R>>> cases = new LinkedList<>();
    private final Function<T, R> otherwise;

    ConditionalFunction(
            @Nonnull final List<Tuple<Predicate<T>, Function<T, R>>> cases,
            @Nonnull final Function<T, R> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public R apply(final T input) {
        for (final Tuple<Predicate<T>, Function<T, R>> kase : cases) {
            if (kase.getFirst().test(input)) {
                return kase.getSecond().apply(input);
            }
        }

        return  otherwise.apply(input);
    }
}
