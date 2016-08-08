package com.github.vvinston.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalBiConsumerTest {

    private final BiPredicate<Boolean, Boolean> predicate = (condition1, condition2) -> condition1;

    @Mock
    private BiConsumer<Boolean, Boolean> success;

    @Mock
    private BiConsumer<Boolean, Boolean> fail;

    @Test
    public void testSuccess() {
        // given
        final BiConsumer<Boolean, Boolean> testSubject = ConditionalBiConsumer
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // when
        testSubject.accept(true, true);

        // then
        Mockito.verify(success, Mockito.times(1)).accept(true, true);
        Mockito.verify(fail, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
    }

    @Test
    public void testFail() {
        // given
        final BiConsumer<Boolean, Boolean> testSubject = ConditionalBiConsumer
                .when(predicate)
                .then(success)
                .otherwise(fail);

        // when
        testSubject.accept(false, true);

        // then
        Mockito.verify(fail, Mockito.times(1)).accept(false, true);
        Mockito.verify(success, Mockito.never()).accept(Mockito.anyBoolean(), Mockito.anyBoolean());
    }
}
