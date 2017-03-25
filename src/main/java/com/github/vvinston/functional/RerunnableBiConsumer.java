package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class RerunnableBiConsumer<T, U> implements BiConsumer<T, U> {

    private final BiConsumer<T, U> consumer;
    private final int numberOfPossibleAttempts;

    public RerunnableBiConsumer(@Nonnull final BiConsumer<T, U> consumer, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.consumer = Objects.requireNonNull(consumer);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input1, final U input2) {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        while (numberOfAttempts < numberOfPossibleAttempts) {
            try {
                consumer.accept(input1, input2);
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        }

        throw new RerunnableException("Could not successfully run consumer!", exceptions);
    }

    public static <T, U> RerunnableBiConsumerBuilder attempt(@Nonnull final BiConsumer<T, U> consumer) {
        return new RerunnableBiConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static final class RerunnableBiConsumerBuilder<T, U> {
        private final BiConsumer<T, U> consumer;

        public RerunnableBiConsumerBuilder(@Nonnull final BiConsumer<T, U> consumer) {
            this.consumer = Objects.requireNonNull(consumer);
        }

        public RerunnableBiConsumer<T, U> times(final int numberOfPossibleAttempts) {
            return new RerunnableBiConsumer<>(consumer, numberOfPossibleAttempts);
        }
    }
}
