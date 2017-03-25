package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

public class GuardedBiConsumer<T, U> implements BiConsumer<T, U> {

    private final Class<? extends RuntimeException> clazz;
    private final BiConsumer<T, U> success;
    private final BiConsumer<T, U> fallback;

    public GuardedBiConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiConsumer<T, U> success,
            @Nonnull final BiConsumer<T, U> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(final T input1, final U input2) {
        try {
            success.accept(input1, input2);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input1, input2);
        }
    }

    public static <T, U> GuardedBiConsumerBuilderStepOne doTry(@Nonnull final BiConsumer<T, U> success) {
        return new GuardedBiConsumerBuilderStepOne(Objects.requireNonNull(success));
    }

    public static class GuardedBiConsumerBuilderStepOne<T, U> {

        private final BiConsumer<T, U> success;

        public GuardedBiConsumerBuilderStepOne(@Nonnull final BiConsumer<T, U> success) {
            this.success = Objects.requireNonNull(success);
        }

        public GuardedBiConsumerBuilderStepTwo<T, U> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedBiConsumerBuilderStepTwo(clazz, Objects.requireNonNull(success));
        }
    }

    public static class GuardedBiConsumerBuilderStepTwo<T, U> {

        private final BiConsumer<T, U> success;
        private final Class<? extends RuntimeException> clazz;

        public GuardedBiConsumerBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final BiConsumer<T, U> success) {
            this.clazz = Objects.requireNonNull(clazz);
            this.success = Objects.requireNonNull(success);
        }

        public GuardedBiConsumer<T, U> fallbackTo(@Nonnull final BiConsumer<T, U> fallback) {
            return new GuardedBiConsumer<>(clazz, success, Objects.requireNonNull(fallback));
        }
    }
}
