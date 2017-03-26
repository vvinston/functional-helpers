package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class RerunnableSupplierBuilder<T> {
    private final Supplier<T> supplier;

    RerunnableSupplierBuilder(@Nonnull final Supplier<T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    public Supplier<T> times(final int numberOfPossibleAttempts) {
        return new RerunnableSupplier<>(supplier, numberOfPossibleAttempts);
    }
}
