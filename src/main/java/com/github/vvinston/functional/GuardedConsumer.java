package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class GuardedConsumer<T> implements Consumer<T> {

    private final Class<? extends RuntimeException> clazz;
    private final Consumer<T> success;
    private final Consumer<T> fallback;

    public GuardedConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Consumer<T> success,
            @Nonnull final Consumer<T> fallback) {
        this.clazz = Preconditions.checkNotNull(clazz);
        this.success = Preconditions.checkNotNull(success);
        this.fallback = Preconditions.checkNotNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input) {
        try {
            success.accept(input);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input);
        }
    }

    public static <T> GuardedConsumerBuilderStepOne doTry(@Nonnull final Consumer<T> success) {
        return new GuardedConsumerBuilderStepOne(Preconditions.checkNotNull(success));
    }

    public static class GuardedConsumerBuilderStepOne<T> {

        private final Consumer<T> success;

        public GuardedConsumerBuilderStepOne(@Nonnull final Consumer<T> success) {
            this.success = Preconditions.checkNotNull(success);
        }

        public GuardedConsumerBuilderStepTwo<T> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedConsumerBuilderStepTwo(clazz, Preconditions.checkNotNull(success));
        }
    }

    public static class GuardedConsumerBuilderStepTwo<T> {

        private final Consumer<T> success;
        private final Class<? extends RuntimeException> clazz;

        public GuardedConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Consumer<T> success) {
            this.clazz = Preconditions.checkNotNull(clazz);
            this.success = Preconditions.checkNotNull(success);
        }

        public GuardedConsumer<T> fallbackTo(@Nonnull final Consumer<T> fallback) {
            return new GuardedConsumer<>(clazz, success, Preconditions.checkNotNull(fallback));
        }
    }
}
