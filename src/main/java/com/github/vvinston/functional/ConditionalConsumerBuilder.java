package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public final class ConditionalConsumerBuilder {
    public <INPUT> ConditionalConsumerBuilderStepOne<INPUT> consumeWhen(@Nonnull final Predicate<INPUT> predicate) {
        return new ConditionalConsumerBuilderStepOne<>(predicate);
    }
}
