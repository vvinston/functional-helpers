package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class NullableFunction<INPUT, RESULT> implements Function<INPUT, RESULT> {

    private final Function<INPUT, Optional<RESULT>> success;
    private final Function<INPUT, RESULT> fallback;

    public NullableFunction(@Nonnull final Function<INPUT, Optional<RESULT>> success, @Nonnull final Function<INPUT, RESULT> fallback) {
        this.success = Objects.requireNonNull(success);
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public RESULT apply(@Nullable final INPUT input) {
        final Optional<RESULT> result = success.apply(input);
        return result.isPresent() ? result.get() : fallback.apply(input);
    }

}
