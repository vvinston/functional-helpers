package com.github.vvinston.functional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalBiConsumerTest {

    @Mock
    private BiPredicate<Boolean, Boolean> predicate1;

    @Mock
    private BiPredicate<Boolean, Boolean> predicate2;

    @Mock
    private BiConsumer<Boolean, Boolean> consumer1;

    @Mock
    private BiConsumer<Boolean, Boolean> consumer2;

    @Mock
    private BiConsumer<Boolean, Boolean> consumer3;

    @Before
    public void setUp() {
        Mockito.when(predicate1.test(Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(false);
        Mockito.when(predicate2.test(Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(false);
    }

    @Test
    public void testCase1() {
        // given
        final BiConsumer<Boolean, Boolean> testSubject = givenTestSubject();
        Mockito.when(predicate1.test(Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(true);

        // when
        testSubject.accept(true, true);

        // then
        Mockito.verify(consumer1, Mockito.times(1)).accept(true, true);
        Mockito.verify(consumer2, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
        Mockito.verify(consumer3, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
    }

    @Test
    public void testCase2() {
        // given
        final BiConsumer<Boolean, Boolean> testSubject = givenTestSubject();
        Mockito.when(predicate2.test(Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(true);

        // when
        testSubject.accept(true, true);

        // then
        Mockito.verify(consumer1, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
        Mockito.verify(consumer2, Mockito.times(1)).accept(true, true);
        Mockito.verify(consumer3, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
    }

    @Test
    public void testCase3() {
        // given
        final BiConsumer<Boolean, Boolean> testSubject = givenTestSubject();

        // when
        testSubject.accept(true, true);

        // then
        Mockito.verify(consumer1, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
        Mockito.verify(consumer2, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
        Mockito.verify(consumer3, Mockito.times(1)).accept(true, true);
    }

    private ConditionalBiConsumer<Boolean, Boolean> givenTestSubject() {
        return ConditionalBiConsumer
                .when(predicate1).then(consumer1)
                .when(predicate2).then(consumer2)
                .otherwise(consumer3);
    }
}
