package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class GuardedFunction<T, R> implements Function<T, R> {

    private final Class<? extends RuntimeException> clazz;

    private final Function<T, R> success;

    private final Function<T, R> fallback;

    public GuardedFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Function<T, R> success,
            @Nonnull final Function<T, R> fallback) {
        this.clazz = clazz;
        this.success = success;
        this.fallback = fallback;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public R apply(final T input) {
        try {
            return success.apply(input);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input);
        }
    }

    public static <T, R> GuardedFunctionBuilderStepOne doTry(@Nonnull final Function<T, R> success) {
        return new GuardedFunctionBuilderStepOne(success);
    }

    public static class GuardedFunctionBuilderStepOne<T, R> {

        private final Function<T, R> success;

        public GuardedFunctionBuilderStepOne(final Function<T, R> success) {
            this.success = success;
        }

        public GuardedFunctionBuilderStepTwo<T, R> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedFunctionBuilderStepTwo(clazz, success);
        }
    }

    public static class GuardedFunctionBuilderStepTwo<T, R> {

        private final Function<T, R> success;

        private final Class<? extends RuntimeException> clazz;

        public GuardedFunctionBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Function<T, R> success) {
            this.clazz = clazz;
            this.success = success;
        }

        public GuardedFunction<T, R> fallbackTo(@Nonnull final Function<T, R> fallback) {
            return new GuardedFunction<>(clazz, success, fallback);
        }
    }
}

