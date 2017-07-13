package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class GuardedSupplierBuilderStepTwo<RESULT> {

    private final Supplier<RESULT> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedSupplierBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Supplier<RESULT> success) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
    }

    public Supplier<RESULT> fallbackTo(@Nonnull final Supplier<RESULT> fallback) {
        return new GuardedSupplier<>(clazz, success, Objects.requireNonNull(fallback));
    }
}
