package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class RerunnableBiConsumer<INPUT1, INPUT2> implements BiConsumer<INPUT1, INPUT2> {
    private final BiConsumer<INPUT1, INPUT2> consumer;
    private final int numberOfPossibleAttempts;

    public RerunnableBiConsumer(@Nonnull final BiConsumer<INPUT1, INPUT2> consumer, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.consumer = Objects.requireNonNull(consumer);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
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
}
