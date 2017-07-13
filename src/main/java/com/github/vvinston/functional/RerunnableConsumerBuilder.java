package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class RerunnableConsumerBuilder<INPUT> {
    private final Consumer<INPUT> consumer;

    RerunnableConsumerBuilder(@Nonnull final Consumer<INPUT> consumer) {
        this.consumer = Objects.requireNonNull(consumer);
    }

    public Consumer<INPUT> times(final int numberOfPossibleAttempts) {
        return new RerunnableConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
