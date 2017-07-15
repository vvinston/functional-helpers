package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class GuardedSupplierBuilderStepTwo<RESULT> {
    private final Supplier<RESULT> success;
    private final Class<? extends RuntimeException> clazz;

    GuardedSupplierBuilderStepTwo(@Nonnull final Class<? extends RuntimeException> clazz, @Nonnull final Supplier<RESULT> success) {
        this.clazz = clazz;
        this.success = success;
    }

    public Supplier<RESULT> fallbackTo(@Nonnull final Supplier<RESULT> fallback) {
        return new GuardedSupplier<>(clazz, success, fallback);
    }
}
