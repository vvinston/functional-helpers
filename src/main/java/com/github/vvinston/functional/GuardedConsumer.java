package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public final class GuardedConsumer<INPUT> implements Consumer<INPUT> {
    private final Class<? extends RuntimeException> clazz;
    private final Consumer<INPUT> success;
    private final Consumer<INPUT> fallback;

    public GuardedConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Consumer<INPUT> success,
            @Nonnull final Consumer<INPUT> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(@Nullable final INPUT input) {
        try {
            success.accept(input);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input);
        }
    }
}
