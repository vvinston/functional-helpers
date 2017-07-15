package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class RerunnableSupplierBuilderStepOne<RESULT> {
    private final Supplier<RESULT> supplier;

    RerunnableSupplierBuilderStepOne(@Nonnull final Supplier<RESULT> supplier) {
        this.supplier = supplier;
    }

    public Supplier<RESULT> times(final int numberOfPossibleAttempts) {
        return new RerunnableSupplier<>(supplier, numberOfPossibleAttempts);
    }
}
