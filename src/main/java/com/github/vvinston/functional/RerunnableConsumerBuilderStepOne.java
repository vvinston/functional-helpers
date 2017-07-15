package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class RerunnableConsumerBuilderStepOne<INPUT> {
    private final Consumer<INPUT> consumer;

    RerunnableConsumerBuilderStepOne(@Nonnull final Consumer<INPUT> consumer) {
        this.consumer = consumer;
    }

    public Consumer<INPUT> times(final int numberOfPossibleAttempts) {
        return new RerunnableConsumer<>(consumer, numberOfPossibleAttempts);
    }
}
