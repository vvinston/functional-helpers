package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class ConditionalBiFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final BiPredicate<Boolean, Boolean> predicate = (condition1, condition2) -> condition1;
    private final BiFunction<Boolean, Boolean, String> success = (input1, input2) -> SUCCESS;
    private final BiFunction<Boolean, Boolean, String> fail = (input1, input2) -> FAIL;

    @Test
    public void testHappyPath() {
        // given
        final BiFunction<Boolean, Boolean, String> testSubject = ConditionalBiFunction
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true, true));
        Assert.assertEquals(FAIL, testSubject.apply(false, true));
    }
}
