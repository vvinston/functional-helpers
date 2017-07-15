package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class GuardedFunctionBuilder {
    public <INPUT, RESULT> GuardedFunctionBuilderStepOne doTry(@Nonnull final Function<INPUT, RESULT> success) {
        return new GuardedFunctionBuilderStepOne<>(success);
    }
}
