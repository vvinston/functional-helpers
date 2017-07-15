package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class RerunnableSupplierBuilder {
    public <T> RerunnableSupplierBuilderStepOne attempt(@Nonnull final Supplier<T> supplier) {
        return new RerunnableSupplierBuilderStepOne<>(supplier);
    }
}
