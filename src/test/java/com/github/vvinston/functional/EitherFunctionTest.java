package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class EitherFunctionTest {

    private static final String LEFT_RESULT = "test";
    private static final Integer RIGHT_RESULT = 12;

    private final Predicate<Boolean> predicate = condition -> condition;
    private final Function<Boolean, String> leftFunction = input -> LEFT_RESULT;
    private final Function<Boolean, Integer> rightFunction = input -> RIGHT_RESULT;

    @Test
    public void testHappyPath() {
        // given
        final Function<Boolean, Either<String, Integer>> testSubject = Functions
                .either(predicate)
                .then(leftFunction)
                .otherwise(rightFunction);

        // then
        Assert.assertEquals(Optional.of(LEFT_RESULT), testSubject.apply(true).getLeft());
        Assert.assertEquals(Optional.of(RIGHT_RESULT), testSubject.apply(false).getRight());
    }
}
