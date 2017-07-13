package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

public final class RerunnableFunctionBuilder<INPUT, RESULT> {
    private final Function<INPUT, RESULT> function;

    RerunnableFunctionBuilder(@Nonnull final Function<INPUT, RESULT> function) {
        this.function = Objects.requireNonNull(function);
    }

    public Function<INPUT, RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableFunction<>(function, numberOfPossibleAttempts);
    }
}
