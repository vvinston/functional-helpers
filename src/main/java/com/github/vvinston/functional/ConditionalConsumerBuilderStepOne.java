package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepOne<INPUT> {
    private final Predicate<INPUT> predicate;

    ConditionalConsumerBuilderStepOne(@Nonnull final Predicate<INPUT> predicate) {
        this.predicate = predicate;
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalConsumerBuilderStepTwo<INPUT> then(@Nonnull final Consumer<INPUT> success) {
        final List<Tuple<Predicate<INPUT>, Consumer<INPUT>>> cases = new LinkedList<>();
        cases.add(SimpleTuple.of(predicate, success));
        return new ConditionalConsumerBuilderStepTwo<>(cases);
    }
}
