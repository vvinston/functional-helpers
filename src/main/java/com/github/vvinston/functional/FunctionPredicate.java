package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class FunctionPredicate<INPUT> implements Predicate<INPUT> {
    private final Function<INPUT, Boolean> function;

    public FunctionPredicate(@Nonnull final Function<INPUT, Boolean> function) {
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public boolean test(@Nullable final INPUT input) {
        return function.apply(input);
    }
}
