package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import java.util.function.Function;

public class GuardedFunctionTest {

    private final Class<? extends RuntimeException> clazz = IllegalArgumentException.class;

    private final Function<Boolean, String> success1 = input -> "success";

    private final Function<Boolean, String> success2 = input -> { throw new IllegalArgumentException("x"); };

    private final Function<Boolean, String> success3 = input -> { throw new NullPointerException("y"); };

    private final Function<Boolean, String> fallback = input -> "fallback";

    @Test
    public void testSuccess() {
        final Function<Boolean, String> function = GuardedFunction.doTry(success1).inCaseOf(clazz).fallbacktTo(fallback);
        final String result = function.apply(true);
        Assert.assertEquals("success", result);
    }

    @Test
    public void testFallback() {
        final Function<Boolean, String> function = GuardedFunction.doTry(success2).inCaseOf(clazz).fallbacktTo(fallback);
        final String result = function.apply(true);
        Assert.assertEquals("fallback", result);
    }

    @Test(expected = NullPointerException.class)
    public void testFallbackWithUnexpectedException() {
        final Function<Boolean, String> function = GuardedFunction.doTry(success3).inCaseOf(clazz).fallbacktTo(fallback);
        function.apply(true);
    }
}
