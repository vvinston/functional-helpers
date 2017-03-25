package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class ConditionalBiConsumer<T, U> implements BiConsumer<T, U> {

    private final BiPredicate<T, U> predicate;
    private final BiConsumer<T, U> success;
    private final BiConsumer<T, U> fail;

    public ConditionalBiConsumer(
            @Nonnull final BiPredicate<T, U> predicate,
            @Nonnull final BiConsumer<T, U> success,
            @Nonnull final BiConsumer<T, U> fail) {
        this.predicate = Objects.requireNonNull(predicate);
        this.success = Objects.requireNonNull(success);
        this.fail = Objects.requireNonNull(fail);
    }

    @Override
    public void accept(@Nullable final T input1, @Nullable final U input2) {
        if (predicate.test(input1, input2)) {
            success.accept(input1, input2);
        } else {
            fail.accept(input1, input2);
        }
    }

    public static <T, U> ConditionalBiConsumerBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalBiConsumerBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public ConditionalBiConsumerBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalBiConsumerBuilderStepTwo<T, U> then(@Nonnull final BiConsumer<T, U> success) {
            return new ConditionalBiConsumerBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
        }
    }

    public static final class ConditionalBiConsumerBuilderStepTwo<T, U> {

        private final BiPredicate<T, U> predicate;
        private final BiConsumer<T, U> success;

        private ConditionalBiConsumerBuilderStepTwo(@Nonnull final BiPredicate<T, U> predicate, @Nonnull final BiConsumer<T, U> success) {
            this.predicate = Objects.requireNonNull(predicate);
            this.success = Objects.requireNonNull(success);
        }

        public ConditionalBiConsumer<T, U> otherwise(@Nonnull final BiConsumer<T, U> fail) {
            return new ConditionalBiConsumer<>(predicate, success, Objects.requireNonNull(fail));
        }
    }
}
