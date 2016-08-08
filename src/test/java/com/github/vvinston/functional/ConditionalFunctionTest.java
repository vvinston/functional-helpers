package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final Predicate<Boolean> predicate = condition -> condition;
    private final Function<Boolean, String> success = input -> SUCCESS;
    private final Function<Boolean, String> fail = input -> FAIL;

    @Test
    public void testHappyPath() {
        // given
        final Function<Boolean, String> testSubject = ConditionalFunction
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true));
        Assert.assertEquals(FAIL, testSubject.apply(false));
    }
}
