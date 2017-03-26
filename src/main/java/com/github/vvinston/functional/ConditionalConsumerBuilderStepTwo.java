package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilderStepTwo<T> {

    private final List<Tuple<Predicate<T>, Consumer<T>>> cases;

    ConditionalConsumerBuilderStepTwo(@Nonnull final List<Tuple<Predicate<T>, Consumer<T>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalConsumerBuilderStepThree<T> consumeWhen(@Nonnull final Predicate<T> predicate) {
        return new ConditionalConsumerBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
    }

    public Consumer<T> otherwise(@Nonnull final Consumer<T> otherwise) {
        return new ConditionalConsumer<>(cases, Objects.requireNonNull(otherwise));
    }
}
