package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiPredicate;

public final class ConditionalBiConsumerBuilder {
    public <INPUT1, INPUT2> ConditionalBiConsumerBuilderStepOne<INPUT1, INPUT2> consumeWhen(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        return new ConditionalBiConsumerBuilderStepOne<>(predicate);
    }
}
