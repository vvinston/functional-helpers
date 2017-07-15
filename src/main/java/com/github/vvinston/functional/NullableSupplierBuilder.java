package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public final class NullableSupplierBuilder {
    public <RESULT> Supplier<RESULT> nullableWithFallbackValue(
            @Nonnull final Supplier<Optional<RESULT>> success, final RESULT fallbackValue) {
        return nullableWithFallback(success, () -> fallbackValue);
    }

    public <RESULT> Supplier<RESULT> nullableWithFallback(
            @Nonnull final Supplier<Optional<RESULT>> success, final Supplier<RESULT> fallback) {
        return new NullableSupplier<>(success, fallback);
    }
}
