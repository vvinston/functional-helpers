package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilder {
    public <INPUT1, INPUT2> ConditionalBiFunctionBuilderStepOne<INPUT1, INPUT2> when(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        return new ConditionalBiFunctionBuilderStepOne<>(predicate);
    }
}
