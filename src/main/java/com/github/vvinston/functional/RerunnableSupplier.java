package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

final class RerunnableSupplier<T> implements Supplier<T> {

    private final Supplier<T> supplier;
    private final int numberOfPossibleAttempts;

    RerunnableSupplier(@Nonnull final Supplier<T> supplier, final int numberOfPossibleAttempts) {
        if (numberOfPossibleAttempts < 0) {
            throw new IllegalArgumentException("Number of possible attempts can not be negative!");
        }

        this.supplier = Objects.requireNonNull(supplier);
        this.numberOfPossibleAttempts = numberOfPossibleAttempts;
    }

    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:illegalcatch"})
    @Override
    public T get() {
        final List<RuntimeException> exceptions = new ArrayList<>();
        int numberOfAttempts = 0;

        while (numberOfAttempts < numberOfPossibleAttempts) {
            try {
                return supplier.get();
            } catch (final RuntimeException exception) {
                exceptions.add(exception);
                numberOfAttempts++;
            }
        }

        throw new RerunnableException("Could not successfully run supplier!", exceptions);
    }
}
