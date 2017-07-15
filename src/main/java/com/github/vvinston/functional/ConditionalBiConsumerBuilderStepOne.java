package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepOne<INPUT1, INPUT2> {
    private final BiPredicate<INPUT1, INPUT2> predicate;

    ConditionalBiConsumerBuilderStepOne(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        this.predicate = predicate;
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalBiConsumerBuilderStepTwo<INPUT1, INPUT2> then(@Nonnull final BiConsumer<INPUT1, INPUT2> success) {
        final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases = new LinkedList<>();
        cases.add(SimpleTuple.of(predicate, success));
        return new ConditionalBiConsumerBuilderStepTwo<>(cases);
    }
}
