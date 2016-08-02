package com.github.vvinston.functional;

import java.util.function.Function;

public class GuardedFunction<T, R> implements Function<T, R> {

    private final Class<? extends RuntimeException> clazz;

    private final Function<T, R> success;

    private final Function<T, R> fallback;

    public GuardedFunction(final Class<? extends RuntimeException> clazz, final Function<T, R> success, final Function<T, R> fallback) {
        this.clazz = clazz;
        this.success = success;
        this.fallback = fallback;
    }

    @Override
    public R apply(final T input) {
        try {
            return success.apply(input);
        } catch (final RuntimeException e) {
            if (!clazz.isInstance(e)) {
                throw e;
            }

            return fallback.apply(input);
        }
    }

    public static <T, R> GuardedFunctionBuilderStepOne doTry(final Function<T, R> success) {
        return new GuardedFunctionBuilderStepOne(success);
    }

    public static class GuardedFunctionBuilderStepOne<T, R> {

        private final Function<T, R> success;

        public GuardedFunctionBuilderStepOne(final Function<T, R> success) {
            this.success = success;
        }

        public GuardedFunctionBuilderStepTwo<T, R> inCaseOf(final Class<? extends RuntimeException> clazz) {
            return new GuardedFunctionBuilderStepTwo(clazz, success);
        }
    }

    public static class GuardedFunctionBuilderStepTwo<T, R> {

        private final Function<T, R> success;


        private final Class<? extends RuntimeException> clazz;

        public GuardedFunctionBuilderStepTwo(final Class<? extends RuntimeException> clazz, final Function<T, R> success) {
            this.clazz = clazz;
            this.success = success;
        }

        public GuardedFunction<T, R> fallbacktTo(final Function<T, R> fallback) {
            return new GuardedFunction<>(clazz, success, fallback);
        }
    }
}

