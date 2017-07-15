package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class RerunnableConsumerBuilder {
    public <INPUT> RerunnableConsumerBuilderStepOne attempt(@Nonnull final Consumer<INPUT> consumer) {
        return new RerunnableConsumerBuilderStepOne<>(consumer);
    }
}
