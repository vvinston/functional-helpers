package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class GuardedSupplierBuilderStepOne<T> {

    private final Supplier<T> success;

    GuardedSupplierBuilderStepOne(@Nonnull final Supplier<T> success) {
        this.success = Objects.requireNonNull(success);
    }

    public GuardedSupplierBuilderStepTwo<T> inCaseOf(@Nonnull final Class<? extends RuntimeException> clazz) {
        return new GuardedSupplierBuilderStepTwo(clazz, Objects.requireNonNull(success));
    }
}
