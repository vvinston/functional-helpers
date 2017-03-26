package com.github.vvinston.functional.example;

import com.github.vvinston.functional.Functions;
import com.github.vvinston.functional.RerunnableException;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

public class Example {

    @Test
    public void case1() {
        final int numberOfRetries = 3;
        final Function<String, String> businessFunction = i -> i;
        final Function<String, String> fallbackFunction = i -> i;
        final Function<String, String> composed = Functions
                .doTry(Functions.attempt(businessFunction).times(numberOfRetries))
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

        final Function<String, Query> generateQuery = Functions.deterministic(tokenize.andThen(createQuery));
        final Function<Query, Result> guardedExecute = Functions
                .doTry(Functions.attempt(execute).times(numberOfRetries))
                .inCaseOf(RerunnableException.class)
                .fallbackTo(i -> new Result());
        final Function<Result, Integer> guardedExtract = Functions.nullableWithFallbackValue(extract, 0);

        final Function<String, Integer> acquireData = generateQuery.andThen(guardedExecute).andThen(guardedExtract);
    }

    @Test
    public void case3() {
        final Function<String, Boolean> function1 = i -> false;
        final Function<String, Integer> function2 = i -> 1;
        final Function<String, Integer> function3 = i -> 2;

        final Function<String, Integer> composed = Functions
                .when(Functions.predicateOf(function1))
                .then(function2)
                .otherwise(function3);
    }

    private static class Token { }
    private static class Query { }
    private static class Result { }
}
