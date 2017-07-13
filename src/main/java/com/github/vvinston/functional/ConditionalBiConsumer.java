package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

final class ConditionalBiConsumer<INPUT1, INPUT2> implements BiConsumer<INPUT1, INPUT2> {

    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases = new LinkedList<>();
    private final BiConsumer<INPUT1, INPUT2> otherwise;

    ConditionalBiConsumer(
            @Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases,
            @Nonnull final BiConsumer<INPUT1, INPUT2> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public void accept(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        for (final Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                kase.getSecond().accept(input1, input2);
                return;
            }
        }

        otherwise.accept(input1, input2);
    }
}
