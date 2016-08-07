package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

public class RightTest {

    private static final String PAYLOAD = "test";

    @Test
    public void testInstantiate() {
        // given
        final Right<Integer, String> testSubject = new Right<>(PAYLOAD);

        //
        Assert.assertEquals(PAYLOAD, testSubject.getPayload());
    }

    @Test(expected = NullPointerException.class)
    public void testInstantiateWithNull() {
        // given
        new Right<>(null);
    }

    @Test
    public void testEquals() {
        // given
        final Right<Integer, String> testSubject = new Right<>(PAYLOAD);
        final Right<Integer, String> other1 = new Right<>(PAYLOAD);
        final Right<Integer, String> other2 = new Right<>("foo");
        final Right<String, Integer> other3 = new Right<>(42);
        final Left<String, Integer> other4 = new Left<>(PAYLOAD);

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
        final Right<Integer, String> testSubject1 = new Right<>(PAYLOAD);
        final Right<Integer, String> testSubject2 = new Right<>(PAYLOAD);
        final Right<Integer, String> testSubject3 = new Right<>("foo");

        // then
        Assert.assertEquals(testSubject1.hashCode(), testSubject2.hashCode());
        Assert.assertNotEquals(testSubject1.hashCode(), testSubject3.hashCode());

    }

    @Test
    public void testToString() {
        // given
        final Right<Integer, String> testSubject = new Right<>(PAYLOAD);

        // then
        Assert.assertEquals("Right{payload=test}", testSubject.toString());
    }
}
