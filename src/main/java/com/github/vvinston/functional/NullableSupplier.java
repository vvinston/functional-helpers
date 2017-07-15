package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class NullableSupplier<RESULT> implements Supplier<RESULT> {
    private final Supplier<Optional<RESULT>> success;
    private final Supplier<RESULT> fallback;

    public NullableSupplier(@Nonnull final Supplier<Optional<RESULT>> success, @Nonnull final Supplier<RESULT> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public RESULT get() {
        final Optional<RESULT> result = success.get();
        return result.orElseGet(fallback);
    }
}
