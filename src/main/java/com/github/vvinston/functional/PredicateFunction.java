package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class PredicateFunction<INPUT> implements Function<INPUT, Boolean> {
    private final Predicate<INPUT> predicate;

    public PredicateFunction(@Nonnull final Predicate<INPUT> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @Override
    public Boolean apply(@Nullable final INPUT input) {
        return predicate.test(input);
    }
}
