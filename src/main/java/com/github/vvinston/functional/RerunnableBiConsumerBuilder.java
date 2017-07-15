package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public final class RerunnableBiConsumerBuilder {
    public <INPUT1, INPUT2> RerunnableBiConsumerBuilderStepOne attempt(@Nonnull final BiConsumer<INPUT1, INPUT2> consumer) {
        return new RerunnableBiConsumerBuilderStepOne<>(consumer);
    }
}
