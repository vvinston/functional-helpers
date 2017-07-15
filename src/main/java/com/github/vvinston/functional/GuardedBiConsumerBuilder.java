package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public final class GuardedBiConsumerBuilder {
    public <INPUT1, INPUT2> GuardedBiConsumerBuilderStepOne doTry(@Nonnull final BiConsumer<INPUT1, INPUT2> success) {
        return new GuardedBiConsumerBuilderStepOne<>(success);
    }
}
