package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConditionalConsumer<T> implements Consumer<T> {

    private final Predicate<T> predicate;
    private final Consumer<T> success;
    private final Consumer<T> fail;

    public ConditionalConsumer(
            @Nonnull final Predicate<T> predicate,
            @Nonnull final Consumer<T> success,
            @Nonnull final Consumer<T> fail) {
        this.predicate = Objects.requireNonNull(predicate);
        this.success = Objects.requireNonNull(success);
        this.fail = Objects.requireNonNull(fail);
    }

    @Override
    public void accept(@Nonnull final T input) {
        if (predicate.test(input)) {
            success.accept(input);
        } else {
            fail.accept(input);
        }
    }

    public static <T> ConditionalConsumerBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return new ConditionalConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static final class ConditionalConsumerBuilderStepOne<T> {

        private final Predicate<T> predicate;

        public ConditionalConsumerBuilderStepOne(@Nonnull final Predicate<T> predicate) {
            this.predicate = Objects.requireNonNull(predicate);
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ConditionalConsumerBuilderStepTwo<T> then(@Nonnull final Consumer<T> success) {
            return new ConditionalConsumerBuilderStepTwo<>(predicate, Objects.requireNonNull(success));
        }
    }

    public static final class ConditionalConsumerBuilderStepTwo<T> {

        private final Predicate<T> predicate;
        private final Consumer<T> success;

        private ConditionalConsumerBuilderStepTwo(@Nonnull final Predicate<T> predicate, @Nonnull final Consumer<T> success) {
            this.predicate = Objects.requireNonNull(predicate);
            this.success = Objects.requireNonNull(success);
        }

        public ConditionalConsumer<T> otherwise(@Nonnull final Consumer<T> fail) {
            return new ConditionalConsumer<>(predicate, success, Objects.requireNonNull(fail));
        }
    }
}
