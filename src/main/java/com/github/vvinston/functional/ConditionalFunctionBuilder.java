package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilder {
    public <INPUT> ConditionalFunctionBuilderStepOne<INPUT> when(@Nonnull final Predicate<INPUT> predicate) {
        return new ConditionalFunctionBuilderStepOne<>(predicate);
    }
}
