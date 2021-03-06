package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class GuardedFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FALLBACK = "fallback";

    private final Class<? extends RuntimeException> clazz = IllegalArgumentException.class;

    private final Function<Boolean, String> success1 = input -> SUCCESS;
    private final Function<Boolean, String> success2 = input -> { throw new IllegalArgumentException("x"); };
    private final Function<Boolean, String> success3 = input -> { throw new NullPointerException("y"); };
    private final Function<Boolean, String> fallback = input -> FALLBACK;

    @Test
    public void testSuccess() {
        // given
        @SuppressWarnings("unchecked")
        final Function<Boolean, String> testSubject = new GuardedFunction<>(clazz, success1, fallback);

        // when
        final String result = testSubject.apply(true);

        // then
        Assert.assertEquals(SUCCESS, result);
    }

    @Test
    public void testFallback() {
        // given
        @SuppressWarnings("unchecked")
        final Function<Boolean, String> testSubject = new GuardedFunction<>(clazz, success2, fallback);

        // when
        final String result = testSubject.apply(true);

        // then
        Assert.assertEquals(FALLBACK, result);
    }

    @Test(expected = NullPointerException.class)
    public void testFallbackWithUnexpectedException() {
        // given
        @SuppressWarnings("unchecked")
        final Function<Boolean, String> testSubject = new GuardedFunction<>(clazz, success3, fallback);

        // when
        testSubject.apply(true);
    }
}
