package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiPredicate;

public final class EitherBiFunctionBuilder {
    public <INPUT1, INPUT2> EitherBiFunctionBuilderStepOne<INPUT1, INPUT2> either(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        return new EitherBiFunctionBuilderStepOne<>(predicate);
    }
}
