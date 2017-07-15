package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class RerunnableFunctionBuilder {
    public <INPUT, RESULT> RerunnableFunctionBuilderStepOne attempt(@Nonnull final Function<INPUT, RESULT> function) {
        return new RerunnableFunctionBuilderStepOne<>(function);
    }
}
