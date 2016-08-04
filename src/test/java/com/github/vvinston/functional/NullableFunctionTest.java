package com.github.vvinston.functional;


import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

public class NullableFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FALLBACK = "fallback";

    private final Function<Boolean, Optional<String>> success1 = input -> Optional.of(SUCCESS);
    private final Function<Boolean, Optional<String>> success2 = input -> Optional.empty();
    private final Function<Boolean, String> fallback = input -> FALLBACK;

    @Test
    public void testWhenSuccessIsNotNullThenReturnWithSuccessValue() {
        // given
        final Function<Boolean, String> testSubject = new NullableFunction<>(success1, fallback);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true));
    }

    @Test
    public void testWhenSuccessIsNullThenReturnWithFallbackValue() {
        // given
        final Function<Boolean, String> testSubject = new NullableFunction<>(success2, fallback);

        // then
        Assert.assertEquals(FALLBACK, testSubject.apply(true));
    }

    @Test
    public void testWhenSuccessIsNullThenReturnWithConstantFallbackValue() {
        // given
        final Function<Boolean, String> testSubject = NullableFunction.getFunctionWithConstantFallbackValue(success2, FALLBACK);

        // then
        Assert.assertEquals(FALLBACK, testSubject.apply(true));
    }
}
