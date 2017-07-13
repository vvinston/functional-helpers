package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiConsumer;

final class GuardedBiConsumer<INPUT1, INPUT2> implements BiConsumer<INPUT1, INPUT2> {

    private final Class<? extends RuntimeException> clazz;
    private final BiConsumer<INPUT1, INPUT2> success;
    private final BiConsumer<INPUT1, INPUT2> fallback;

    GuardedBiConsumer(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiConsumer<INPUT1, INPUT2> success,
            @Nonnull final BiConsumer<INPUT1, INPUT2> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public void accept(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        try {
            success.accept(input1, input2);
        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            fallback.accept(input1, input2);
        }
    }
}
