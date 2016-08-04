package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RerunnableExceptionTest {

    private static final String MESSAGE = "test";

    @Test(expected = RuntimeException.class)
    public void testRerunnableExceptionIsRuntimeException() {
        // when
        throw new RerunnableException(MESSAGE, Collections.emptyList());
    }

    @Test
    public void testInstantiate() {
        // given
        final List<RuntimeException> causes = new ArrayList<>();
        final RerunnableException testSubject = new RerunnableException(MESSAGE, causes);

        // then
        Assert.assertEquals(MESSAGE, testSubject.getMessage());
        Assert.assertSame(causes, testSubject.getCauses());
        Assert.assertNotNull(testSubject.getStackTrace());
        Assert.assertEquals(0, testSubject.getStackTrace().length);
    }
}
