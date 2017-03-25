package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class RerunnableSupplier<T> implements Supplier<T> {

    private final Supplier<T> supplier;
    private final int numberOfPossibleAttempts;

    public RerunnableSupplier(@Nonnull final Supplier<T> supplier, final int numberOfPossibleAttempts) {
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

    public static <T> RerunnableSupplierBuilder attempt(@Nonnull final Supplier<T> supplier) {
        return new RerunnableSupplierBuilder(Objects.requireNonNull(supplier));
    }

    public static final class RerunnableSupplierBuilder<T> {
        private final Supplier<T> supplier;

        public RerunnableSupplierBuilder(@Nonnull final Supplier<T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        public RerunnableSupplier<T> times(final int numberOfPossibleAttempts) {
            return new RerunnableSupplier<>(supplier, numberOfPossibleAttempts);
        }
    }
}
