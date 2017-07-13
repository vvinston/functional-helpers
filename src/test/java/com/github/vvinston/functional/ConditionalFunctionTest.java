package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFunctionTest {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final Predicate<Boolean> predicate = condition -> condition;
    private final Function<Boolean, String> success = input -> SUCCESS;
    private final Function<Boolean, String> fail = input -> FAIL;

    private final Predicate<String> predicate1 = "a"::equals;
    private final Predicate<String> predicate2 = "b"::equals;
    private final Predicate<String> predicate3 = "c"::equals;
    private final Function<String, Integer> function1 = input -> 1;
    private final Function<String, Integer> function2 = input -> 2;
    private final Function<String, Integer> function3 = input -> 3;
    private final Function<String, Integer> function4 = input -> 4;

    @Test
    public void testHappyPath() {
        // given
        final List<Tuple<Predicate<Boolean>, Function<Boolean, String>>> cases = new ArrayList<>();
        cases.add(SimpleTuple.of(predicate, success));
        final Function<Boolean, String> testSubject = new ConditionalFunction<>(cases, fail);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true));
        Assert.assertEquals(FAIL, testSubject.apply(false));
    }

    @Test
    public void testMultipleCases() {
        // given
        final List<Tuple<Predicate<String>, Function<String, Integer>>> cases = new ArrayList<>();
        cases.add(SimpleTuple.of(predicate1, function1));
        cases.add(SimpleTuple.of(predicate2, function2));
        cases.add(SimpleTuple.of(predicate3, function3));
        final Function<String, Integer> testSubject = new ConditionalFunction<>(cases, function4);

        // then
        Assert.assertEquals(Integer.valueOf(1), testSubject.apply("a"));
        Assert.assertEquals(Integer.valueOf(2), testSubject.apply("b"));
        Assert.assertEquals(Integer.valueOf(3), testSubject.apply("c"));
        Assert.assertEquals(Integer.valueOf(4), testSubject.apply("d"));
        Assert.assertEquals(Integer.valueOf(4), testSubject.apply(null));
    }
}
