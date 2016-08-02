package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunctionTest {

    final Predicate<Boolean> predicate = condition -> condition;

    final Function<Boolean, String> success = input -> "success";

    final Function<Boolean, String> fail = input -> "fail";

    @Test
    public void testHappyPath() {
        final Function<Boolean, String> conditionalFunction = ConditionalFunction
                .when(predicate)
                .then(success)
                .otherwise(fail);

        Assert.assertEquals("success", conditionalFunction.apply(true));
        Assert.assertEquals("fail", conditionalFunction.apply(false));
    }
}
