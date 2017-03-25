package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class CachedFunctionsPerformanceTest {

    private static final Function<Integer, Long> TEST_FUNCTION = i -> (long) i * i * i;
    private static final int TEST_LENGTH = 10_000;
    private static final int NUMBER_OF_ITERATIONS = 20;

    private final String testName;
    private final Function<Integer, Long> testSubject;

    public CachedFunctionsPerformanceTest(final String testName, final Function testSubject) {
        this.testName = testName;
        this.testSubject = testSubject;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Deterministic::HashMap", DeterministicFunction.getMapCachedFunction(TEST_FUNCTION) },
                { "Deterministic::ConcurrentHashMap", new DeterministicFunction(new MapCache(new ConcurrentHashMap()), TEST_FUNCTION) },
                { "Deterministic::IdentityHashMap", new DeterministicFunction(new MapCache(new IdentityHashMap()), TEST_FUNCTION) },
                { "Deterministic::LinkedHashMap", new DeterministicFunction(new MapCache(new LinkedHashMap()), TEST_FUNCTION) },
                { "MemoicFunction::HashMap", MemoicFunction.getMapCachedFunction(TEST_FUNCTION) },
                { "MemoicFunction::ConcurrentHashMap", new MemoicFunction(new ConcurrentHashMap(), TEST_FUNCTION) },
                { "MemoicFunction::IdentityHashMap", new MemoicFunction(new IdentityHashMap(), TEST_FUNCTION) },
                { "MemoicFunction::LinkedHashMap", new MemoicFunction(new LinkedHashMap(), TEST_FUNCTION) },
        });
    }

    @Test
    public void testFunctionPerformance() {
        // given
        final List<List<Long>> results = new ArrayList<>();

        // when
        for (int n = 0; n < NUMBER_OF_ITERATIONS; n++) {
            final List<Long> result = new ArrayList<>(2 * TEST_LENGTH);
            final long startTime = System.currentTimeMillis();

            for (int i = 1; i <= TEST_LENGTH; i++) {
                result.add(testSubject.apply(i));
            }

            results.add(result);
            final long endTime = System.currentTimeMillis();
            final long calculatedTime = endTime - startTime;
            System.out.println("Test [" + testName + "] iteration " + n + " ("
                    + (n == 0 ? "calculation" : "cache") + ") finished. Time: " + calculatedTime + "ms");
        }

        // then
        for (int n = 1; n < NUMBER_OF_ITERATIONS; n++) {
            Assert.assertEquals(results.get(0), results.get(n));
        }
    }
}
