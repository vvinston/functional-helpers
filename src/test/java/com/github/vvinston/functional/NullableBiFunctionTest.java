package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.BiFunction;

public class NullableBiFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FALLBACK = "fallback";

    private final BiFunction<Boolean, Boolean, Optional<String>> success1 = (input1, input2) -> Optional.of(SUCCESS);
    private final BiFunction<Boolean, Boolean, Optional<String>> success2 = (input1, input2) -> Optional.empty();
    private final BiFunction<Boolean, Boolean, String> fallback = (input1, input2) -> FALLBACK;

    @Test
    public void testWhenSuccessIsNotNullThenReturnWithSuccessValue() {
        // given
        final BiFunction<Boolean, Boolean, String> testSubject = new NullableBiFunction<>(success1, fallback);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true, true));
    }

    @Test
    public void testWhenSuccessIsNullThenReturnWithFallbackValue() {
        // given
        final BiFunction<Boolean, Boolean, String> testSubject = new NullableBiFunction<>(success2, fallback);

        // then
        Assert.assertEquals(FALLBACK, testSubject.apply(true, true));
    }

    @Test
    public void testWhenSuccessIsNullThenReturnWithConstantFallbackValue() {
        // given
        final BiFunction<Boolean, Boolean, String> testSubject = new NullableBiFunctionBuilder()
                .nullableWithFallbackValue(success2, FALLBACK);

        // then
        Assert.assertEquals(FALLBACK, testSubject.apply(true, true));
    }
}
