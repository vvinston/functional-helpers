package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public final class EitherFunctionBuilder {
    public <INPUT> EitherFunctionBuilderStepOne<INPUT> either(@Nonnull final Predicate<INPUT> predicate) {
        return new EitherFunctionBuilderStepOne<>(predicate);
    }
}
