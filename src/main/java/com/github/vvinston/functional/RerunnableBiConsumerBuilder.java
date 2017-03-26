package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class RerunnableBiConsumerBuilder<T, U> {
    private final BiConsumer<T, U> consumer;

    RerunnableBiConsumerBuilder(@Nonnull final BiConsumer<T, U> consumer) {
        this.consumer = Objects.requireNonNull(consumer);
    }

    public BiConsumer<T, U> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
