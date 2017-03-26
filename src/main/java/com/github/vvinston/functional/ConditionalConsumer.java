package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class ConditionalConsumer<T> implements Consumer<T> {

    private final List<Tuple<Predicate<T>, Consumer<T>>> cases = new LinkedList<>();
    private final Consumer<T> otherwise;

    ConditionalConsumer(
            @Nonnull final List<Tuple<Predicate<T>, Consumer<T>>> cases,
            @Nonnull final Consumer<T> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nonnull final T input) {
        for (final Tuple<Predicate<T>, Consumer<T>> kase : cases) {
            if (kase.getFirst().test(input)) {
                kase.getSecond().accept(input);
                return;
            }
        }

        otherwise.accept(input);
    }
}
