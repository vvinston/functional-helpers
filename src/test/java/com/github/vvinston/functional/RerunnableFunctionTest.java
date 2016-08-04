package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

@RunWith(MockitoJUnitRunner.class)
public class RerunnableFunctionTest {

    private static final String SUCCESS = "success";

    @Mock
    private Function<Boolean, String> function;

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateWithNegativeNumberOfAttempts() {
        // given
        new RerunnableFunction<>(function, -3);
    }

    @Test
    public void testSuccessfulRunningWillReturnWithResult() {
        // given
        final Function<Boolean, String> testSubject = RerunnableFunction.attempt(function).times(1);
        Mockito.when(function.apply(Mockito.anyBoolean())).thenReturn(SUCCESS);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true));
    }

    @Test
    public void testAfterSuccessfulAttemptWillReturnWithResult() {
        // given
        final Function<Boolean, String> testSubject = RerunnableFunction.attempt(function).times(2);
        Mockito.when(function.apply(Mockito.anyBoolean())).thenThrow(new IllegalArgumentException()).thenReturn(SUCCESS);

        // then
        Assert.assertEquals(SUCCESS, testSubject.apply(true));
    }

    @Test
    @SuppressWarnings("checkstyle:illegalcatch")
    public void testZeroTimesWillNotCallDecorated() {
        // given
        final Function<Boolean, String> testSubject = RerunnableFunction.attempt(function).times(0);

        // when
        try {
            testSubject.apply(true);
            Assert.fail();
        } catch (final Exception exception) {
            Assert.assertTrue(exception instanceof RerunnableException);
        }

        // then
        Mockito.verify(function, Mockito.never()).apply(Mockito.anyBoolean());
    }

    @Test
    @SuppressWarnings("checkstyle:illegalcatch")
    public void testExpectedNumberOfTriesWillBeExecuted() {
        // given
        final int numberOfAttempts = 5;
        final Function<Boolean, String> testSubject = RerunnableFunction.attempt(function).times(numberOfAttempts);
        Mockito.when(function.apply(Mockito.anyBoolean())).thenThrow(new IllegalArgumentException());

        // when
        try {
            testSubject.apply(true);
            Assert.fail();
        } catch (final Exception exception) {
            Assert.assertTrue(exception instanceof RerunnableException);

            if (exception instanceof RerunnableException) {
                for (int i = 0; i < numberOfAttempts; i++) {
                    Assert.assertTrue(((RerunnableException) exception).getCauses().get(i) instanceof IllegalArgumentException);
                }
            }
        }

        // then
        Mockito.verify(function, Mockito.times(numberOfAttempts)).apply(Mockito.anyBoolean());
    }
}
