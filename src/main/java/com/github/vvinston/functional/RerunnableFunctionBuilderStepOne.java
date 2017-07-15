package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class RerunnableFunctionBuilderStepOne<INPUT, RESULT> {
    private final Function<INPUT, RESULT> function;

    RerunnableFunctionBuilderStepOne(@Nonnull final Function<INPUT, RESULT> function) {
        this.function = function;
    }

    public Function<INPUT, RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableFunction<>(function, numberOfPossibleAttempts);
    }
}
