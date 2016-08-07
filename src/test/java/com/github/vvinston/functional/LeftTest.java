package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

public class LeftTest {

    private static final Integer PAYLOAD = 42;

    @Test
    public void testInstantiate() {
        // given
        final Left<Integer, String> testSubject = new Left<>(PAYLOAD);

        //
        Assert.assertEquals(PAYLOAD, testSubject.getPayload());
    }

    @Test(expected = NullPointerException.class)
    public void testInstantiateWithNull() {
        // given
        new Left<>(null);
    }

    @Test
    public void testEquals() {
        // given
        final Left<Integer, String> testSubject = new Left<>(PAYLOAD);
        final Left<Integer, String> other1 = new Left<>(PAYLOAD);
        final Left<Integer, String> other2 = new Left<>(12);
        final Left<String, Integer> other3 = new Left<>("test");
        final Right<String, Integer> other4 = new Right<>(PAYLOAD);

        // then
        Assert.assertTrue(testSubject.equals(testSubject));
        Assert.assertFalse(testSubject.equals(null));
        Assert.assertTrue(testSubject.equals(other1));
        Assert.assertFalse(testSubject.equals(other2));
        Assert.assertFalse(testSubject.equals(other3));
        Assert.assertFalse(testSubject.equals(other4));
    }

    @Test
    public void testHashCode() {
        // given
        final Left<Integer, String> testSubject1 = new Left<>(PAYLOAD);
        final Left<Integer, String> testSubject2 = new Left<>(PAYLOAD);
        final Left<Integer, String> testSubject3 = new Left<>(12);

        // then
        Assert.assertEquals(testSubject1.hashCode(), testSubject2.hashCode());
        Assert.assertNotEquals(testSubject1.hashCode(), testSubject3.hashCode());

    }

    @Test
    public void testToString() {
        // given
        final Left<Integer, String> testSubject = new Left<>(PAYLOAD);

        // then
        Assert.assertEquals("Left{payload=42}", testSubject.toString());
    }
}
