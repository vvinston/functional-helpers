package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

final class NullableSupplier<T> implements Supplier<T> {

    private final Supplier<Optional<T>> success;
    private final Supplier<T> fallback;

    NullableSupplier(@Nonnull final Supplier<Optional<T>> success, @Nonnull final Supplier<T> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public T get() {
        final Optional<T> result = success.get();
        return result.orElseGet(fallback);
    }
}
