package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;

public final class RerunnableBiFunctionBuilderStepOne<INPUT1, INPUT2, RESULT> {
    private final BiFunction<INPUT1, INPUT2, RESULT> function;

    RerunnableBiFunctionBuilderStepOne(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function) {
        this.function = function;
    }

    public BiFunction<INPUT1, INPUT2, RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableBiFunction<>(function, numberOfPossibleAttempts);
    }
}
