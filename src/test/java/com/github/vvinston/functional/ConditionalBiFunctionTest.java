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

    private final BiPredicate<Boolean, Boolean> predicate1 = (condition1, condition2) -> condition1 && condition2;
    private final BiPredicate<Boolean, Boolean> predicate2 = (condition1, condition2) -> condition1 && !condition2;
    private final BiPredicate<Boolean, Boolean> predicate3 = (condition1, condition2) -> !condition1 && condition2;
    private final BiFunction<Boolean, Boolean, Integer> function1 = (input1, input2) -> 1;
    private final BiFunction<Boolean, Boolean, Integer> function2 = (input1, input2) -> 2;
    private final BiFunction<Boolean, Boolean, Integer> function3 = (input1, input2) -> 3;
    private final BiFunction<Boolean, Boolean, Integer> function4 = (input1, input2) -> 4;

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

    @Test
    public void testMultipleCases() {
        // given
        final BiFunction<Boolean, Boolean, Integer> testSubject = ConditionalBiFunction
                .when(predicate1).then(function1)
                .when(predicate2).then(function2)
                .when(predicate3).then(function3)
                .otherwise(function4);

        // then
        Assert.assertEquals(Integer.valueOf(1), testSubject.apply(true, true));
        Assert.assertEquals(Integer.valueOf(2), testSubject.apply(true, false));
        Assert.assertEquals(Integer.valueOf(3), testSubject.apply(false, true));
        Assert.assertEquals(Integer.valueOf(4), testSubject.apply(false, false));
    }
}
