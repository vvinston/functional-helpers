package com.github.vvinston.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Consumer;

@RunWith(MockitoJUnitRunner.class)
public class GuardedConsumerTest {

    private static final String INPUT = "test";

    private final Class<? extends RuntimeException> clazz = IllegalArgumentException.class;

    @Mock
    private Consumer<String> success;

    @Mock
    private Consumer<String> fallback;

    @Test
    public void testSuccess() {
        // given
        @SuppressWarnings("unchecked")
        final Consumer<String> testSubject = Functions.doTry(success).inCaseOf(clazz).fallbackTo(fallback);

        // when
        testSubject.accept(INPUT);

        // then
        Mockito.verify(success, Mockito.times(1)).accept(INPUT);
        Mockito.verify(fallback, Mockito.never()).accept(INPUT);
    }

    @Test
    public void testFallback() {
        // given
        @SuppressWarnings("unchecked")
        final Consumer<String> testSubject = Functions.doTry(success).inCaseOf(clazz).fallbackTo(fallback);
        Mockito.doThrow(IllegalArgumentException.class).when(success).accept(Mockito.anyString());

        // when
        testSubject.accept("test");

        // then
        Mockito.verify(success, Mockito.times(1)).accept(INPUT);
        Mockito.verify(fallback, Mockito.times(1)).accept(INPUT);
    }

    @Test(expected = NullPointerException.class)
    public void testFallbackWithUnexpectedException() {
        // given
        @SuppressWarnings("unchecked")
        final Consumer<String> testSubject = Functions.doTry(success).inCaseOf(clazz).fallbackTo(fallback);
        Mockito.doThrow(NullPointerException.class).when(success).accept(Mockito.anyString());

        // when
        testSubject.accept("test");
    }
}
