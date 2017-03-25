package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class NullableSupplier<T> implements Supplier<T> {

    private final Supplier<Optional<T>> success;
    private final Supplier<T> fallback;

    public NullableSupplier(@Nonnull final Supplier<Optional<T>> success, @Nonnull final Supplier<T> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public T get() {
        final Optional<T> result = success.get();
        return result.orElseGet(fallback);
    }

    public static <T> NullableSupplier<T> getSupplierWithConstantFallbackValue(@Nonnull final Supplier<Optional<T>> success, final T fallbackValue) {
        final Supplier<T> fallback = () -> fallbackValue;
        return new NullableSupplier<>(Objects.requireNonNull(success), fallback);
    }
}
