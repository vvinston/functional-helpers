package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilderStepTwo<T, U> {

    private final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases;

    ConditionalBiConsumerBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<T, U>, BiConsumer<T, U>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalBiConsumerBuilderStepThree<T, U> consumeWhen(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiConsumerBuilderStepThree<>(predicate, cases);
    }

    public BiConsumer<T, U> otherwise(@Nonnull final BiConsumer<T, U> otherwise) {
        return new ConditionalBiConsumer<>(cases, Objects.requireNonNull(otherwise));
    }
}
