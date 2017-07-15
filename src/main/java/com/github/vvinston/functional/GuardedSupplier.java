package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public final class GuardedSupplier<RESULT> implements Supplier<RESULT> {
    private final Class<? extends RuntimeException> clazz;
    private final Supplier<RESULT> success;
    private final Supplier<RESULT> fallback;

    public GuardedSupplier(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Supplier<RESULT> success,
            @Nonnull final Supplier<RESULT> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public RESULT get() {
        try {
            return success.get();
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.get();
        }
    }
}
