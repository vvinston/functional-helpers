package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
        final List<Tuple<BiPredicate<Boolean, Boolean>, BiFunction<Boolean, Boolean, String>>> cases = new ArrayList<>();
        cases.add(SimpleTuple.of(predicate, success));
        final BiFunction<Boolean, Boolean, String> testSubject = new ConditionalBiFunction<>(cases, fail);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true, true));
        Assert.assertEquals(FAIL, testSubject.apply(false, true));
    }

    @Test
    public void testMultipleCases() {
        // given
        final List<Tuple<BiPredicate<Boolean, Boolean>, BiFunction<Boolean, Boolean, Integer>>> cases = new ArrayList<>();
        cases.add(SimpleTuple.of(predicate1, function1));
        cases.add(SimpleTuple.of(predicate2, function2));
        cases.add(SimpleTuple.of(predicate3, function3));
        final BiFunction<Boolean, Boolean, Integer> testSubject = new ConditionalBiFunction<>(cases, function4);

        // then
        Assert.assertEquals(Integer.valueOf(1), testSubject.apply(true, true));
        Assert.assertEquals(Integer.valueOf(2), testSubject.apply(true, false));
        Assert.assertEquals(Integer.valueOf(3), testSubject.apply(false, true));
        Assert.assertEquals(Integer.valueOf(4), testSubject.apply(false, false));
    }
}
