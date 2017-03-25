package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;

public class GuardedBiFunction<T, U, R> implements BiFunction<T, U, R> {

    private final Class<? extends RuntimeException> clazz;
    private final BiFunction<T, U, R> success;
    private final BiFunction<T, U, R> fallback;

    public GuardedBiFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiFunction<T, U, R> success,
            @Nonnull final BiFunction<T, U, R> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input1, final U input2) {
        try {
            return success.apply(input1, input2);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input1, input2);
        }
    }

    public static <T, U, R> GuardedBiFunctionBuilderStepOne doTry(@Nonnull final BiFunction<T, U, R> success) {
        return new GuardedBiFunctionBuilderStepOne(Objects.requireNonNull(success));
    }


    public static class GuardedBiFunctionBuilderStepOne<T, U, R> {
        private final BiFunction<T, U, R> success;

        public GuardedBiFunctionBuilderStepOne(@Nonnull final BiFunction<T, U, R> success) {
            this.success = success;
        }

        public GuardedBiFunctionBuilderStepTwo<T, U, R> inCase(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedBiFunctionBuilderStepTwo<T, U, R>(success, clazz);
        }
    }

    public static class GuardedBiFunctionBuilderStepTwo<T, U, R> {
        private final BiFunction<T, U, R> success;
        private final Class<? extends RuntimeException> clazz;

        public GuardedBiFunctionBuilderStepTwo(@Nonnull final BiFunction<T, U, R> success, @Nonnull final Class<? extends RuntimeException> clazz) {
            this.success = success;
            this.clazz = clazz;
        }

        public GuardedBiFunction<T, U, R> fallbackTo(@Nonnull final BiFunction<T, U, R> fallback) {
            return new GuardedBiFunction<>(clazz, success, fallback);
        }
    }
}
