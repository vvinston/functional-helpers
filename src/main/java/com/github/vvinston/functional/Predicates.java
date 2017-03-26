package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.ExcessivePublicCount"})
public final class Predicates {

    private Predicates() {
        throw new AssertionError("This class should not be instantiated!");
    }

    public static <T> Function<T, Boolean> functionOf(@Nonnull final Predicate<T> predicate) {
        return new PredicateFunction<T>(predicate);
    }

    public static <T, U> BiFunction<T, U, Boolean> functionOf(@Nonnull final BiPredicate<T, U> predicate) {
        return new PredicateBiFunction<T, U>(predicate);
    }
}
