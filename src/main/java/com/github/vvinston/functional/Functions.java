package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.Objects;
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

    public static <T> ConditionalFunction.ConditionalFunctionBuilderStepOne<T> when(@Nonnull final Predicate<T> predicate) {
        return ConditionalFunction.when(predicate);
    }

    public static <T, U> ConditionalBiFunction.ConditionalBiFunctionBuilderStepOne<T, U> when(@Nonnull final BiPredicate<T, U> predicate) {
        return ConditionalBiFunction.when(predicate);
    }

    public static <T> ConditionalConsumer.ConditionalConsumerBuilderStepOne<T> consumeWhen(@Nonnull final Predicate<T> predicate) {
        return ConditionalConsumer.when(predicate);
    }

    public static <T, U> ConditionalBiConsumer.ConditionalBiConsumerBuilderStepOne<T, U> consumeWhen(@Nonnull final BiPredicate<T, U> predicate) {
        return ConditionalBiConsumer.when(predicate);
    }

    public static <T> EitherFunction.EitherFunctionBuilderStepOne<T> eitherWhen(@Nonnull final Predicate<T> predicate) {
        return new EitherFunction.EitherFunctionBuilderStepOne<>(Objects.requireNonNull(predicate));
    }

    public static <T, U> EitherBiFunction.EitherBiFunctionBuilderStepOne<T, U> eitherWhen(@Nonnull final BiPredicate<T, U> predicate) {
        return new EitherBiFunction.EitherBiFunctionBuilderStepOne<T, U>(Objects.requireNonNull(predicate));
    }

    public static <T, R> Function<T, R> getCached(@Nonnull final Function<T, R> function) {
        return DeterministicFunction.getMapCachedFunction(function);
    }

    public static <T, R> Function<T, R> getCached(@Nonnull final Function<T, R> function, @Nonnull final Cache<T, R> cache) {
        return new DeterministicFunction(cache, function);
    }

    public static <T, U, R> BiFunction<T, U, R> getCached(@Nonnull final BiFunction<T, U, R> function) {
        return DeterministicBiFunction.getMapCachedFunction(function);
    }

    public static <T, U, R> BiFunction<T, U, R> getCached(@Nonnull final BiFunction<T, U, R> function, @Nonnull final Cache<T, R> cache) {
        return new DeterministicBiFunction(cache, function);
    }

    public static <T, R> Function<T, R> getMapCached(@Nonnull final Function<T, R> function) {
        return MemoicFunction.getMapCachedFunction(function);
    }

    public static <T, R> GuardedFunction.GuardedFunctionBuilderStepOne doTry(@Nonnull final Function<T, R> success) {
        return new GuardedFunction.GuardedFunctionBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, U, R> GuardedBiFunction.GuardedBiFunctionBuilderStepOne doTry(@Nonnull final BiFunction<T, U, R> success) {
        return new GuardedBiFunction.GuardedBiFunctionBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T> GuardedConsumer.GuardedConsumerBuilderStepOne doTry(@Nonnull final Consumer<T> success) {
        return new GuardedConsumer.GuardedConsumerBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, U> GuardedBiConsumer.GuardedBiConsumerBuilderStepOne doTry(@Nonnull final BiConsumer<T, U> success) {
        return new GuardedBiConsumer.GuardedBiConsumerBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T> GuardedSupplier.GuardedSupplierBuilderStepOne doTry(@Nonnull final Supplier<T> success) {
        return new GuardedSupplier.GuardedSupplierBuilderStepOne(Objects.requireNonNull(success));
    }

    public static <T, R> RerunnableFunction.RerunnableFunctionBuilder attempt(@Nonnull final Function<T, R> function) {
        return new RerunnableFunction.RerunnableFunctionBuilder(Objects.requireNonNull(function));
    }

    public static <T, U, R> RerunnableBiFunction.RerunnableBiFunctionBuilderStepOne attempt(@Nonnull final BiFunction<T, U, R> function) {
        return new RerunnableBiFunction.RerunnableBiFunctionBuilderStepOne(Objects.requireNonNull(function));
    }

    public static <T> RerunnableConsumer.RerunnableConsumerBuilder attempt(@Nonnull final Consumer<T> consumer) {
        return new RerunnableConsumer.RerunnableConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static <T, U> RerunnableBiConsumer.RerunnableBiConsumerBuilder attempt(@Nonnull final BiConsumer<T, U> consumer) {
        return new RerunnableBiConsumer.RerunnableBiConsumerBuilder(Objects.requireNonNull(consumer));
    }

    public static <T> RerunnableSupplier.RerunnableSupplierBuilder attempt(@Nonnull final Supplier<T> supplier) {
        return new RerunnableSupplier.RerunnableSupplierBuilder(Objects.requireNonNull(supplier));
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
