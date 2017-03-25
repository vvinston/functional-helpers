package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public class GuardedSupplier<T> implements Supplier<T> {

    private final Class<? extends RuntimeException> clazz;
    private final Supplier<T> success;
    private final Supplier<T> fallback;

    public GuardedSupplier(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Supplier<T> success,
            @Nonnull final Supplier<T> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public T get() {
        try {
            return success.get();
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.get();
        }
    }

    public static <T> GuardedSupplierBuilderStepOne doTry(@Nonnull final Supplier<T> success) {
        return new GuardedSupplierBuilderStepOne(Objects.requireNonNull(success));
    }

    public static class GuardedSupplierBuilderStepOne<T> {

        private final Supplier<T> success;

        public GuardedSupplierBuilderStepOne(@Nonnull final Supplier<T> success) {
            this.success = Objects.requireNonNull(success);
        }

        public GuardedSupplierBuilderStepTwo<T> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
            return new GuardedSupplierBuilderStepTwo(clazz, Objects.requireNonNull(success));
        }
    }

    public static class GuardedSupplierBuilderStepTwo<T> {

        private final Supplier<T> success;
        private final Class<? extends RuntimeException> clazz;

        public GuardedSupplierBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Supplier<T> success) {
            this.clazz = Objects.requireNonNull(clazz);
            this.success = Objects.requireNonNull(success);
        }

        public GuardedSupplier<T> fallbackTo(@Nonnull final Supplier<T> fallback) {
            return new GuardedSupplier<>(clazz, success, Objects.requireNonNull(fallback));
        }
    }
}
