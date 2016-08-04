package com.github.vvinston.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MapCacheTest {

    private static final String SUCCESS = "success";

    @Mock
    private Map<Boolean, String> map;

    @Test
    public void testNewCacheContainsNoValues() {
        // given
        final Cache<Boolean, String> testSubject = new MapCache<>(new HashMap<>());

        // then
        Assert.assertFalse(testSubject.exists(true));
        Assert.assertFalse(testSubject.exists(false));
        Assert.assertNull(testSubject.get(true));
        Assert.assertNull(testSubject.get(false));
    }

    @Test
    public void testCacheContainsAddedElements() {
        // given
        final Cache<Boolean, String> testSubject = new MapCache<>(new HashMap<>());

        // when
        testSubject.put(true, SUCCESS);

        // then
        Assert.assertTrue(testSubject.exists(true));
        Assert.assertEquals(SUCCESS, testSubject.get(true));
    }

    @Test
    public void testUnderlyingMapIsCalledProperly() {
        // given
        final Cache<Boolean, String> testSubject = new MapCache<>(map);

        // when
        testSubject.exists(true);
        testSubject.put(true, SUCCESS);
        testSubject.get(true);

        // then
        final InOrder inOrder = Mockito.inOrder(map);
        inOrder.verify(map).containsKey(true);
        inOrder.verify(map).put(true, SUCCESS);
        inOrder.verify(map).get(true);
    }
}
