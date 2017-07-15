package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public final class RerunnableBiConsumerBuilderStepOne<INPUT1, INPUT2> {
    private final BiConsumer<INPUT1, INPUT2> consumer;

    RerunnableBiConsumerBuilderStepOne(@Nonnull final BiConsumer<INPUT1, INPUT2> consumer) {
        this.consumer = consumer;
    }

    public BiConsumer<INPUT1, INPUT2> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
