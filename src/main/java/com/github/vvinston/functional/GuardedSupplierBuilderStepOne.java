package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class GuardedSupplierBuilderStepOne<RESULT> {
    private final Supplier<RESULT> success;

    GuardedSupplierBuilderStepOne(@Nonnull final Supplier<RESULT> success) {
        this.success = success;
    }

    public GuardedSupplierBuilderStepTwo<RESULT> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedSupplierBuilderStepTwo<>(clazz, success);
    }
}
