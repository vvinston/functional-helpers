package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class RerunnableConsumer<T> implements Consumer<T> {

    private final Consumer<T> consumer;
    private final int numberOfPossibleAttempts;

    public RerunnableConsumer(@Nonnull final Consumer<T> consumer, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.consumer = Objects.requireNonNull(consumer);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input) {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        while (numberOfAttempts < numberOfPossibleAttempts) {
            try {
                consumer.accept(input);
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        }

        throw new RerunnableException("Could not successfully run consumer!", exceptions);
    }

    public static <T> RerunnableConsumerBuilder attempt(@Nonnull final Consumer<T> consumer) {
        return new RerunnableConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static final class RerunnableConsumerBuilder<T> {
        private final Consumer<T> consumer;

        public RerunnableConsumerBuilder(@Nonnull final Consumer<T> consumer) {
            this.consumer = Objects.requireNonNull(consumer);
        }

        public RerunnableConsumer<T> times(final int numberOfPossibleAttempts) {
            return new RerunnableConsumer<>(consumer, numberOfPossibleAttempts);
        }
    }
}