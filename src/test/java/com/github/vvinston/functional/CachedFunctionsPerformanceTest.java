package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class CachedFunctionsPerformanceTest {

    private static final Function<Integer, Long> TEST_FUNCTION = i -> (long) i * i * i * i;
    private static final int TEST_LENGTH = 25_000;
    private static final int NUMBER_OF_ITERATIONS = 15;

    private final String testName;
    private final Function<Integer, Long> testSubject;

    public CachedFunctionsPerformanceTest(final String testName, final Function<Integer, Long> testSubject) {
        this.testName = testName;
        this.testSubject = testSubject;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        //noinspection unchecked
        return Arrays.asList(new Object[][] {
                { "Deterministic::HashMap", new DeterministicFunctionBuilder().deterministic(TEST_FUNCTION) },
                { "Deterministic::ConcurrentHashMap", new DeterministicFunction<>(TEST_FUNCTION, new MapCache<>(new ConcurrentHashMap<>())) },
                { "Deterministic::IdentityHashMap", new DeterministicFunction<>(TEST_FUNCTION, new MapCache<>(new IdentityHashMap<>())) },
                { "Deterministic::LinkedHashMap", new DeterministicFunction<>(TEST_FUNCTION, new MapCache<>(new LinkedHashMap<>())) },
                { "MemoicFunction::HashMap", new MemoicFunctionBuilder().memoic(TEST_FUNCTION) },
                { "MemoicFunction::ConcurrentHashMap", new MemoicFunction<>(TEST_FUNCTION, new ConcurrentHashMap<>()) },
                { "MemoicFunction::IdentityHashMap", new MemoicFunction<>(TEST_FUNCTION, new IdentityHashMap<>()) },
                { "MemoicFunction::LinkedHashMap", new MemoicFunction<>(TEST_FUNCTION, new LinkedHashMap<>()) },
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
            System.out.println("Performance test [" + testName + "] iteration " + n + " ("
                    + (n == 0 ? "calculation" : "cache") + ") finished. Time: " + calculatedTime + "ms");
        }

        // then
        for (int n = 1; n < NUMBER_OF_ITERATIONS; n++) {
            Assert.assertEquals(results.get(0), results.get(n));
        }
    }
}
