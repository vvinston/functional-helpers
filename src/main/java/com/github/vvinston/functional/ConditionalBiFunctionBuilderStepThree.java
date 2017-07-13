package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepThree<INPUT1, INPUT2, RESULT> {

    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases;
    private final BiPredicate<INPUT1, INPUT2> predicate;

    ConditionalBiFunctionBuilderStepThree(
            @Nonnull final BiPredicate<INPUT1, INPUT2> predicate,
            @Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases) {
        this.predicate = Objects.requireNonNull(predicate);
        this.cases = Objects.requireNonNull(cases);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT> then(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success) {
        cases.add(SimpleTuple.of(predicate, Objects.requireNonNull(success)));
        return new ConditionalBiFunctionBuilderStepTwo<>(cases);
    }
}
