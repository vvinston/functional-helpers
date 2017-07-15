package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RightAppliedConsumer<INPUT1, INPUT2> implements Consumer<INPUT1> {
    private final BiConsumer<INPUT1, INPUT2> biConsumer;
    private final INPUT2 boundParameter;

    public RightAppliedConsumer(@Nonnull final BiConsumer<INPUT1, INPUT2> biConsumer, @Nullable final INPUT2 boundParameter) {
        this.biConsumer = Objects.requireNonNull(biConsumer);
        this.boundParameter = boundParameter;
    }

    @Override
    public void accept(@Nullable final INPUT1 input) {
        biConsumer.accept(input, boundParameter);
    }
}
