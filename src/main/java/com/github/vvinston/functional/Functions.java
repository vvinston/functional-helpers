package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Functions {

    private Functions() {
        throw new AssertionError("This class should not be instantiated!");
    }

    public static <T> Function<T, Boolean> functionOf(@Nonnull final Predicate<T> predicate) {
        return new PredicateFunction<T>(predicate);
    }

    public static <T, U> BiFunction<T, U, Boolean> functionOf(@Nonnull final BiPredicate<T, U> predicate) {
        return new PredicateBiFunction<T, U>(predicate);
    }

    public static <T> Predicate<T> predicateOf(@Nonnull final Function<T, Boolean> function) {
        return new FunctionPredicate<T>(function);
    }

    public static <T, U> BiPredicate<T, U> predicateOf(@Nonnull final BiFunction<T, U, Boolean> function) {
        return new FunctionBiPredicate<T, U>(function);
    }
}
