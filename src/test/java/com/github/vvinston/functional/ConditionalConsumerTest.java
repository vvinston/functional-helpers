package com.github.vvinston.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Consumer;
import java.util.function.Predicate;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalConsumerTest {

    private final Predicate<Boolean> predicate = condition -> condition;

    @Mock
    private Consumer<Boolean> success;

    @Mock
    private Consumer<Boolean> fail;

    @Test
    public void testSuccess() {
        // given
        final Consumer<Boolean> testSubject = ConditionalConsumer
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // when
        testSubject.accept(true);

        // then
        Mockito.verify(success, Mockito.times(1)).accept(true);
        Mockito.verify(fail, Mockito.never()).accept(Mockito.anyBoolean());
    }

    @Test
    public void testFail() {
        // given
        final Consumer<Boolean> testSubject = ConditionalConsumer
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // when
        testSubject.accept(false);

        // then
        Mockito.verify(fail, Mockito.times(1)).accept(false);
        Mockito.verify(success, Mockito.never()).accept(Mockito.anyBoolean());
    }
}
