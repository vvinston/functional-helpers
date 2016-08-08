package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
        this.predicate = Preconditions.checkNotNull(predicate);
        this.success = Preconditions.checkNotNull(success);
        this.fail = Preconditions.checkNotNull(fail);
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
        return new ConditionalBiConsumerBuilderStepOne<>(Preconditions.checkNotNull(predicate));
    }

    public static final class ConditionalBiConsumerBuilderStepOne<T, U> {

        private final BiPredicate<T, U> predicate;

        public ConditionalBiConsumerBuilderStepOne(@Nonnull final BiPredicate<T, U> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalBiConsumerBuilderStepTwo<T, U> then(@Nonnull final BiConsumer<T, U> success) {
            return new ConditionalBiConsumerBuilderStepTwo<>(predicate, Preconditions.checkNotNull(success));
        }
    }

    public static final class ConditionalBiConsumerBuilderStepTwo<T, U> {

        private final BiPredicate<T, U> predicate;
        private final BiConsumer<T, U> success;

        private ConditionalBiConsumerBuilderStepTwo(@Nonnull final BiPredicate<T, U> predicate, @Nonnull final BiConsumer<T, U> success) {
            this.predicate = Preconditions.checkNotNull(predicate);
            this.success = Preconditions.checkNotNull(success);
        }

        public ConditionalBiConsumer<T, U> otherwise(@Nonnull final BiConsumer<T, U> fail) {
            return new ConditionalBiConsumer<>(predicate, success, Preconditions.checkNotNull(fail));
        }
    }
}
