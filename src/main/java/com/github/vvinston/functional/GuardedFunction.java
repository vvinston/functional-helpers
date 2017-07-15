package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;

public final class GuardedFunction<INPUT, RESULT> implements Function<INPUT, RESULT> {
    private final Class<? extends RuntimeException> clazz;
    private final Function<INPUT, RESULT> success;
    private final Function<INPUT, RESULT> fallback;

    public GuardedFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final Function<INPUT, RESULT> success,
            @Nonnull final Function<INPUT, RESULT> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public RESULT apply(@Nullable final INPUT input) {
        try {
            return success.apply(input);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input);
        }
    }
}

