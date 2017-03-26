package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class ConditionalBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases = new LinkedList<>();
    private final BiFunction<T, U, R> otherwise;

    ConditionalBiFunction(
            @Nonnull final List<Tuple<BiPredicate<T, U>, BiFunction<T, U, R>>> cases,
            @Nonnull final BiFunction<T, U, R> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public R apply(@Nonnull final T input1, @Nonnull final U input2) {
        for (final Tuple<BiPredicate<T, U>, BiFunction<T, U, R>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                return kase.getSecond().apply(input1, input2);
            }
        }

        return  otherwise.apply(input1, input2);
    }
}
