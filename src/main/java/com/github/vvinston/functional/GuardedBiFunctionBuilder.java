package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;

public final class GuardedBiFunctionBuilder {
    public <INPUT1, INPUT2, RESULT> GuardedBiFunctionBuilderStepOne doTry(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> success) {
        return new GuardedBiFunctionBuilderStepOne<>(success);
    }
}
