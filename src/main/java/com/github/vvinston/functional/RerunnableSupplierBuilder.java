package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class RerunnableSupplierBuilder<RESULT> {
    private final Supplier<RESULT> supplier;

    RerunnableSupplierBuilder(@Nonnull final Supplier<RESULT> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    public Supplier<RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableSupplier<>(supplier, numberOfPossibleAttempts);
    }
}
