package com.github.vvinston.functional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalConsumerTest {

    @Mock
    private Predicate<Boolean> predicate1;

    @Mock
    private Predicate<Boolean> predicate2;

    @Mock
    private Consumer<Boolean> consumer1;

    @Mock
    private Consumer<Boolean> consumer2;

    @Mock
    private Consumer<Boolean> consumer3;

    @Before
    public void setUp() {
        Mockito.when(predicate1.test(Mockito.anyBoolean())).thenReturn(false);
        Mockito.when(predicate2.test(Mockito.anyBoolean())).thenReturn(false);
    }

    @Test
    public void testCase1() {
        // given
        final Consumer<Boolean> testSubject = givenTestSubject();
        Mockito.when(predicate1.test(Mockito.anyBoolean())).thenReturn(true);

        // when
        testSubject.accept(false);

        // then
        Mockito.verify(consumer1, Mockito.times(1)).accept(false);
        Mockito.verify(consumer2, Mockito.never()).accept(Mockito.anyBoolean());
        Mockito.verify(consumer3, Mockito.never()).accept(Mockito.anyBoolean());
    }

    @Test
    public void testCase2() {
        // given
        final Consumer<Boolean> testSubject = givenTestSubject();
        Mockito.when(predicate2.test(Mockito.anyBoolean())).thenReturn(true);

        // when
        testSubject.accept(false);

        // then
        Mockito.verify(consumer1, Mockito.never()).accept(Mockito.anyBoolean());
        Mockito.verify(consumer2, Mockito.times(1)).accept(false);
        Mockito.verify(consumer3, Mockito.never()).accept(Mockito.anyBoolean());
    }

    @Test
    public void testCase3() {
        // given
        final Consumer<Boolean> testSubject = givenTestSubject();

        // when
        testSubject.accept(false);

        // then
        Mockito.verify(consumer1, Mockito.never()).accept(Mockito.anyBoolean());
        Mockito.verify(consumer2, Mockito.never()).accept(Mockito.anyBoolean());
        Mockito.verify(consumer3, Mockito.times(1)).accept(false);
    }

    private Consumer<Boolean> givenTestSubject() {
        final List<Tuple<Predicate<Boolean>, Consumer<Boolean>>> cases = new ArrayList<>();
        cases.add(SimpleTuple.of(predicate1, consumer1));
        cases.add(SimpleTuple.of(predicate2, consumer2));
        return new ConditionalConsumer<>(cases, consumer3);
    }
}
