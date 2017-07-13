package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class ConditionalBiFunction<INPUT1, INPUT2, RESULT> implements BiFunction<INPUT1, INPUT2, RESULT> {

    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases = new LinkedList<>();
    private final BiFunction<INPUT1, INPUT2, RESULT> otherwise;

    ConditionalBiFunction(
            @Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public RESULT apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        for (final Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>> kase : cases) {
            if (kase.getFirst().test(input1, input2)) {
                return kase.getSecond().apply(input1, input2);
            }
        }

        return  otherwise.apply(input1, input2);
    }
}
