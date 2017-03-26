package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.ExcessivePublicCount"})
public final class Functions {

    private Functions() {
        throw new AssertionError("This class should not be instantiated!");
    }

    public static <T> ConditionalFunctionBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return new ConditionalFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T, U> ConditionalBiFunctionBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T> ConditionalConsumerBuilderStepOne<T> consumeWhen(@Nonnull final Predicate<T> predicate) {
        return new ConditionalConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T, U> ConditionalBiConsumerBuilderStepOne<T, U> consumeWhen(@Nonnull final BiPredicate<T, U> predicate) {
        return new ConditionalBiConsumerBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T> EitherFunctionBuilderStepOne<T> either(@Nonnull final Predicate<T> predicate) {
        return new EitherFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T, U> EitherBiFunctionBuilderStepOne<T, U> either(@Nonnull final BiPredicate<T, U> predicate) {
        return new EitherBiFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T, R> Function<T, R> deterministic(@Nonnull final Function<T, R> function) {
        return deterministic(function, () -> new HashMap<>());
    }

    public static <T, R> Function<T, R> deterministic(@Nonnull final Function<T, R> function, @Nonnull final Supplier<Map<T, R>> supplier) {
        return new DeterministicFunction<>(new MapCache<>(supplier.get()), Objects.requireNonNull(function));
    }

    public static <T, R> Function<T, R> deterministic(@Nonnull final Function<T, R> function, @Nonnull final Cache<T, R> cache) {
        return new DeterministicFunction(cache, function);
    }

    public static <T, U, R> BiFunction<T, U, R> deterministic(@Nonnull final BiFunction<T, U, R> function) {
        return deterministic(function, () -> new HashMap<>());
    }

    public static <T, U, R> BiFunction<T, U, R> deterministic(
            @Nonnull final BiFunction<T, U, R> function,
            @Nonnull final Supplier<Map<Tuple<T, U>, R>> supplier) {
        return new DeterministicBiFunction<>(new MapCache<>(supplier.get()), Objects.requireNonNull(function));
    }

    public static <T, U, R> BiFunction<T, U, R> deterministic(@Nonnull final BiFunction<T, U, R> function, @Nonnull final Cache<T, R> cache) {
        return new DeterministicBiFunction(cache, function);
    }

    public static <T, R> Function<T, R> memoic(@Nonnull final Function<T, R> function) {
        return new MemoicFunction<>(new HashMap<>(), Objects.requireNonNull(function));
    }

    public static <T, R> GuardedFunctionBuilderStepOne doTry(@Nonnull final Function<T, R> success) {
        return new GuardedFunctionBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, U, R> GuardedBiFunctionBuilderStepOne doTry(@Nonnull final BiFunction<T, U, R> success) {
        return new GuardedBiFunctionBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T> GuardedConsumerBuilderStepOne doTry(@Nonnull final Consumer<T> success) {
        return new GuardedConsumerBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, U> GuardedBiConsumerBuilderStepOne doTry(@Nonnull final BiConsumer<T, U> success) {
        return new GuardedBiConsumerBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T> GuardedSupplierBuilderStepOne doTry(@Nonnull final Supplier<T> success) {
        return new GuardedSupplierBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, R> RerunnableFunctionBuilder attempt(@Nonnull final Function<T, R> function) {
        return new RerunnableFunctionBuilder(Objects.requireNonNull(function));
    }

    public static <T, U, R> RerunnableBiFunctionBuilder attempt(@Nonnull final BiFunction<T, U, R> function) {
        return new RerunnableBiFunctionBuilder(Objects.requireNonNull(function));
    }

    public static <T> RerunnableConsumerBuilder attempt(@Nonnull final Consumer<T> consumer) {
        return new RerunnableConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static <T, U> RerunnableBiConsumerBuilder attempt(@Nonnull final BiConsumer<T, U> consumer) {
        return new RerunnableBiConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static <T> RerunnableSupplierBuilder attempt(@Nonnull final Supplier<T> supplier) {
        return new RerunnableSupplierBuilder(Objects.requireNonNull(supplier));
    }

    public static <T> Predicate<T> predicateOf(@Nonnull final Function<T, Boolean> function) {
        return new FunctionPredicate<>(function);
    }

    public static <T, U> BiPredicate<T, U> predicateOf(@Nonnull final BiFunction<T, U, Boolean> function) {
        return new FunctionBiPredicate<>(function);
    }

    public static <T, R> Function<T, R> nullableWithFallbackValue(
            @Nonnull final Function<T, Optional<R>> success, final R fallbackValue) {
        final Function<T, R> fallback = input -> fallbackValue;
        return new NullableFunction<>(Objects.requireNonNull(success), fallback);
    }

    public static <T, R> Function<T, R> nullableWithFallback(
            @Nonnull final Function<T, Optional<R>> success, @Nonnull final Function<T, R> fallback) {
        return new NullableFunction<>(Objects.requireNonNull(success), fallback);
    }

    public static <T, U, R> BiFunction<T, U, R> nullableWithFallbackValue(
            @Nonnull final BiFunction<T, U, Optional<R>> success, final R fallbackValue) {
        final BiFunction<T, U, R> fallback =  (input1, input2)  -> fallbackValue;
        return new NullableBiFunction<>(Objects.requireNonNull(success), fallback);
    }

    public static <T, U, R> BiFunction<T, U, R> nullableWithFallback(
            @Nonnull final BiFunction<T, U, Optional<R>> success, @Nonnull final BiFunction<T, U, R> fallback) {
        return new NullableBiFunction<>(Objects.requireNonNull(success), fallback);
    }

    public static <T> Supplier<T> nullableWithFallbackValue(
            @Nonnull final Supplier<Optional<T>> success, final T fallbackValue) {
        final Supplier<T> fallback = () -> fallbackValue;
        return new NullableSupplier<>(Objects.requireNonNull(success), fallback);
    }

    public static <T> Supplier<T> nullableWithFallback(
            @Nonnull final Supplier<Optional<T>> success, final Supplier<T> fallback) {
        return new NullableSupplier<>(Objects.requireNonNull(success), fallback);
    }
}
