package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

final class ConditionalBiConsumer<T, U> implements BiConsumer<T, U> {

    private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases = new LinkedList<>();
    private final BiConsumer<T, U> otherwise;

    ConditionalBiConsumer(
            @Nonnull final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases,
            @Nonnull final BiConsumer<T, U> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nullable final T input1, @Nullable final U input2) {
        for (final Tuple<BiPredicate<T, U>, BiConsumer<T, U>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                kase.getSecond().accept(input1, input2);
                return;
            }
        }

        otherwise.accept(input1, input2);
    }
}
