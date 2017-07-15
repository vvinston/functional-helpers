package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class GuardedSupplierBuilder {
    public <RESULT> GuardedSupplierBuilderStepOne doTry(@Nonnull final Supplier<RESULT> success) {
        return new GuardedSupplierBuilderStepOne<>(success);
    }
}
