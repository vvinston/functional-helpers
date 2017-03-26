package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class GuardedSupplierBuilderStepTwo<T> {

    private final Supplier<T> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedSupplierBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Supplier<T> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public Supplier<T> fallbackTo(@Nonnull final Supplier<T> fallback) {
        return new GuardedSupplier<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
