package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class RerunnableConsumerBuilder<T> {
    private final Consumer<T> consumer;

    RerunnableConsumerBuilder(@Nonnull final Consumer<T> consumer) {
        this.consumer = Objects.requireNonNull(consumer);
    }

    public Consumer<T> times(final int numberOfPossibleAttempts) {
        return new RerunnableConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
