package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;

public final class RerunnableBiFunctionBuilder {
    public <INPUT1, INPUT2, RESULT> RerunnableBiFunctionBuilderStepOne attempt(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> function) {
        return new RerunnableBiFunctionBuilderStepOne<>(function);
    }
}
