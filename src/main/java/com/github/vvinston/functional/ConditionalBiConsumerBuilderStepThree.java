package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepThree<INPUT1, INPUT2> {

    private final BiPredicate<INPUT1, INPUT2> predicate;
    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases;

    ConditionalBiConsumerBuilderStepThree(
            @Nonnull final BiPredicate<INPUT1, INPUT2> predicate,
            @Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases) {
        this.predicate = predicate;
        this.cases = cases;
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalBiConsumerBuilderStepTwo<INPUT1, INPUT2> then(@Nonnull final BiConsumer<INPUT1, INPUT2> success) {
        cases.add(SimpleTuple.of(predicate, success));
        return new ConditionalBiConsumerBuilderStepTwo<>(cases);
    }
}
