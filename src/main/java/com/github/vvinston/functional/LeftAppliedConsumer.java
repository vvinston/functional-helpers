package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class LeftAppliedConsumer<INPUT1, INPUT2> implements Consumer<INPUT2> {

    private final BiConsumer<INPUT1, INPUT2> biConsumer;
    private final INPUT1 boundParameter;

    public LeftAppliedConsumer(@Nonnull final BiConsumer<INPUT1, INPUT2> biConsumer, @Nullable final INPUT1 boundParameter) {
        this.biConsumer = Objects.requireNonNull(biConsumer);
        this.boundParameter = boundParameter;
    }

    @Override
    public void accept(@Nullable final INPUT2 input) {
        biConsumer.accept(boundParameter, input);
    }
}
