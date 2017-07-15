package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepThree<INPUT> {
    private final Predicate<INPUT> predicate;
    private final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases;

    ConditionalConsumerBuilderStepThree(
            @Nonnull final Predicate<INPUT> predicate,
            @Nonnull final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases) {
        this.cases = cases;
        this.predicate = predicate;
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalConsumerBuilderStepTwo<INPUT> then(@Nonnull final Consumer<INPUT> success) {
        cases.add(SimpleTuple.of(predicate, success));
        return new ConditionalConsumerBuilderStepTwo<>(cases);
    }
}
