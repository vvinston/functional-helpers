package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;

public final class GuardedBiFunction<INPUT1, INPUT2, RESULT> implements BiFunction<INPUT1, INPUT2, RESULT> {

    private final Class<? extends RuntimeException> clazz;
    private final BiFunction<INPUT1, INPUT2, RESULT> success;
    private final BiFunction<INPUT1, INPUT2, RESULT> fallback;

    public GuardedBiFunction(
            @Nonnull final Class<? extends RuntimeException> clazz,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success,
            @Nonnull final BiFunction<INPUT1, INPUT2, RESULT> fallback) {
        this.clazz = Objects.requireNonNull(clazz);
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public RESULT apply(@Nullable final INPUT1 input1, @Nullable final INPUT2 input2) {
        try {
            return success.apply(input1, input2);

        } catch (final RuntimeException exception) {
            if (!clazz.isInstance(exception)) {
                throw exception;
            }

            return fallback.apply(input1, input2);
        }
    }
}
