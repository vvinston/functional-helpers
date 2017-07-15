package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepOne<INPUT1, INPUT2> {

    private final BiPredicate<INPUT1, INPUT2> predicate;

    ConditionalBiFunctionBuilderStepOne(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        this.predicate = predicate;
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public <RESULT> ConditionalBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT> then(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success) {
        final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases = new LinkedList<>();
        cases.add(SimpleTuple.of(predicate, success));
        return new ConditionalBiFunctionBuilderStepTwo<>(cases);
    }
}
