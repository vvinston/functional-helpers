package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class RerunnableBiConsumerBuilder<INPUT1, INPUT2> {
    private final BiConsumer<INPUT1, INPUT2> consumer;

    RerunnableBiConsumerBuilder(@Nonnull final BiConsumer<INPUT1, INPUT2> consumer) {
        this.consumer = Objects.requireNonNull(consumer);
    }

    public BiConsumer<INPUT1, INPUT2> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
