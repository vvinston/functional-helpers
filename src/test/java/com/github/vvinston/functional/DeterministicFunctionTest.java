package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

@RunWith(MockitoJUnitRunner.class)
public class DeterministicFunctionTest {

    private static final String SUCCESS = "success";

    private final Function<Boolean, String> success = input -> SUCCESS;

    private final Function<String, Long> nonDeterministic = input -> System.currentTimeMillis();

    @Mock
    private Cache<Boolean, String> cache;

    @Test
    public void testFirstCallsDecoratedForEveryInput() {
        // given
        final Function<Boolean, String> testSubject = new DeterministicFunction<>(cache, success);
        Mockito.when(cache.exists(Mockito.anyBoolean())).thenReturn(false);

        // when
        testSubject.apply(true);
        testSubject.apply(false);

        // then
        Mockito.verify(cache, Mockito.times(1)).exists(true);
        Mockito.verify(cache, Mockito.times(1)).exists(false);
        Mockito.verify(cache, Mockito.times(1)).put(true, SUCCESS);
        Mockito.verify(cache, Mockito.times(1)).put(false, SUCCESS);
        Mockito.verify(cache, Mockito.times(1)).get(true);
        Mockito.verify(cache, Mockito.times(1)).get(false);
    }

    @Test
    public void testDecoratedIsNotCalledWhenAlreadyStores() {
        // given
        final int numberOfCalls = 5;
        final Function<Boolean, String> testSubject = new DeterministicFunction<>(cache, success);
        Mockito.when(cache.exists(Mockito.anyBoolean())).thenReturn(true);

        // when
        for (int i = 1; i <= numberOfCalls; i++) {
            testSubject.apply(true);
        }

        // then
        Mockito.verify(cache, Mockito.never()).put(Mockito.same(true), Mockito.anyString());
        Mockito.verify(cache, Mockito.times(numberOfCalls)).exists(true);
        Mockito.verify(cache, Mockito.times(numberOfCalls)).get(true);
    }

    @Test
    public void testFactoryReturnsConsistentValue() throws Exception {
        // given
        final String testInput = "test";
        final Function<String, Long> testSubject = DeterministicFunction.getMapCachedFunction(nonDeterministic);

        // when
        final Long result1 = testSubject.apply(testInput);
        Thread.sleep(10);
        final Long result2 = testSubject.apply(testInput);

        // then
        Assert.assertEquals(result1, result2);
    }
}
