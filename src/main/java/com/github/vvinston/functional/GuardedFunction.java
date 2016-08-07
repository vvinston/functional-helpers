package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

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
        this.clazz = Preconditions.checkNotNull(clazz);
        this.success = Preconditions.checkNotNull(success);
        this.fallback = Preconditions.checkNotNull(fallback);
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
        return new GuardedFunctionBuilderStepOne(Preconditions.checkNotNull(success));
    }

    public static class GuardedFunctionBuilderStepOne<T, R> {

        private final Function<T, R> success;

        public GuardedFunctionBuilderStepOne(@Nonnull final Function<T, R> success) {
            this.success = Preconditions.checkNotNull(success);
        }

        public GuardedFunctionBuilderStepTwo<T, R> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedFunctionBuilderStepTwo(clazz, Preconditions.checkNotNull(success));
        }
    }

    public static class GuardedFunctionBuilderStepTwo<T, R> {

        private final Function<T, R> success;
        private final Class<? extends RuntimeException> clazz;

        public GuardedFunctionBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Function<T, R> success) {
            this.clazz = Preconditions.checkNotNull(clazz);
            this.success = Preconditions.checkNotNull(success);
        }

        public GuardedFunction<T, R> fallbackTo(@Nonnull final Function<T, R> fallback) {
            return new GuardedFunction<>(clazz, success, Preconditions.checkNotNull(fallback));
        }
    }
}

