package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

final class ConditionalFunction<INPUT, RESULT> implements Function<INPUT, RESULT> {

    private final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases = new LinkedList<>();
    private final Function<INPUT, RESULT> otherwise;

    ConditionalFunction(
            @Nonnull final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases,
            @Nonnull final Function<INPUT, RESULT> otherwise) {
        this.cases.addAll(Objects.requireNonNull(cases));
        this.otherwise = Objects.requireNonNull(otherwise);
    }

    @Override
    public RESULT apply(@Nullable final INPUT input) {
        for (final Tuple<Predicate<INPUT>, Function<INPUT, RESULT>> kase : cases) {
            if (kase.getFirst().test(input)) {
                return kase.getSecond().apply(input);
            }
        }

        return  otherwise.apply(input);
    }
}
