package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepTwo<INPUT> {
    private final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases;

    ConditionalConsumerBuilderStepTwo(@Nonnull final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases) {
        this.cases = cases;
    }

    public ConditionalConsumerBuilderStepThree<INPUT> consumeWhen(@Nonnull final Predicate<INPUT> predicate) {
        return new ConditionalConsumerBuilderStepThree<>(predicate, cases);
    }

    public Consumer<INPUT> otherwise(@Nonnull final Consumer<INPUT> otherwise) {
        return new ConditionalConsumer<>(cases, otherwise);
    }
}
