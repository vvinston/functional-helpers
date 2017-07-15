package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepTwo<INPUT1, INPUT2> {
    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases;

    ConditionalBiConsumerBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiConsumer<INPUT1, INPUT2>>> cases) {
        this.cases = cases;
    }

    public ConditionalBiConsumerBuilderStepThree<INPUT1, INPUT2> consumeWhen(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        return new ConditionalBiConsumerBuilderStepThree<>(predicate, cases);
    }

    public BiConsumer<INPUT1, INPUT2> otherwise(@Nonnull final BiConsumer<INPUT1, INPUT2> otherwise) {
        return new ConditionalBiConsumer<>(cases, otherwise);
    }
}
