package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class EitherTest {

    private static final Integer LEFT = 42;
    private static final String RIGHT = "test";

    @Test
    public void testLeftInvariants() {
        // given
        final Either<Integer, String> testSubject = Either.left(LEFT);

        // when
        final Optional<Integer> payload = testSubject.getLeft();
        final Optional<String> other = testSubject.getRight();

        // then
        Assert.assertTrue(testSubject.isLeft());
        Assert.assertFalse(testSubject.isRight());
        Assert.assertNotNull(payload);
        Assert.assertNotNull(other);
        Assert.assertSame(LEFT, payload.get());
        Assert.assertFalse(other.isPresent());
    }

    @Test
    public void testRightInvariants() {
        // given
        final Either<Integer, String> testSubject = Either.right(RIGHT);

        // when
        final Optional<String> payload = testSubject.getRight();
        final Optional<Integer> other = testSubject.getLeft();

        // then
        Assert.assertTrue(testSubject.isRight());
        Assert.assertFalse(testSubject.isLeft());
        Assert.assertNotNull(payload);
        Assert.assertNotNull(other);
        Assert.assertSame(RIGHT, payload.get());
        Assert.assertFalse(other.isPresent());
    }
}
