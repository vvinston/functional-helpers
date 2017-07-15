package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class GuardedConsumerBuilder {
    public <INPUT> GuardedConsumerBuilderStepOne doTry(@Nonnull final Consumer<INPUT> success) {
        return new GuardedConsumerBuilderStepOne<>(success);
    }
}
