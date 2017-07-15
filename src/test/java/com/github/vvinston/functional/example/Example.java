package com.github.vvinston.functional.example;

import com.github.vvinston.functional.ConditionalFunctionBuilder;
import com.github.vvinston.functional.DeterministicFunctionBuilder;
import com.github.vvinston.functional.FunctionPredicate;
import com.github.vvinston.functional.GuardedFunctionBuilder;
import com.github.vvinston.functional.NullableFunctionBuilder;
import com.github.vvinston.functional.RerunnableException;
import com.github.vvinston.functional.RerunnableFunctionBuilder;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

public class Example {

    @Test
    public void case1() {
        final int numberOfRetries = 3;
        final Function<String, String> businessFunction = i -> i;
        final Function<String, String> fallbackFunction = i -> i;
        @SuppressWarnings("unchecked")
        final Function<String, String> composed = new GuardedFunctionBuilder()
                .doTry(new RerunnableFunctionBuilder().attempt(businessFunction).times(numberOfRetries))
                .inCaseOf(RerunnableException.class)
                .fallbackTo(fallbackFunction);

        final String result = composed.apply("example");
    }

    @Test
    public void case2() {
        final int numberOfRetries = 3;
        final Function<String, Token> tokenize = i -> new Token();
        final Function<Token, Query> createQuery = i -> new Query();
        final Function<Query, Result> execute = i -> new Result();
        final Function<Result, Optional<Integer>> extract = i -> Optional.of(1);
        final Function<Query, Result> fallback = i -> new Result();

        final Function<String, Query> generateQuery = new DeterministicFunctionBuilder().deterministic(tokenize.andThen(createQuery));
        @SuppressWarnings("unchecked")
        final Function<Query, Result> guardedExecute = new GuardedFunctionBuilder()
                .doTry(new RerunnableFunctionBuilder().attempt(execute).times(numberOfRetries))
                .inCaseOf(RerunnableException.class)
                .fallbackTo(fallback);
        final Function<Result, Integer> guardedExtract = new NullableFunctionBuilder().nullableWithFallbackValue(extract, 0);

        final Function<String, Integer> acquireData = generateQuery.andThen(guardedExecute).andThen(guardedExtract);
    }

    @Test
    public void case3() {
        final Function<String, Boolean> function1 = i -> false;
        final Function<String, Integer> function2 = i -> 1;
        final Function<String, Integer> function3 = i -> 2;

        final Function<String, Integer> composed = new ConditionalFunctionBuilder()
                .when(new FunctionPredicate<>(function1))
                .then(function2)
                .otherwise(function3);
    }

    private static class Token { }
    private static class Query { }
    private static class Result { }
}
