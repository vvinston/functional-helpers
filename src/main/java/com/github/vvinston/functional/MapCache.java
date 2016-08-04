package com.github.vvinston.functional;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Map;

public class MapCache<K, V> implements Cache<K, V> {

    private final Map<K, V> map;

    public MapCache(@Nonnull final Map<K, V> map) {
        this.map = Preconditions.checkNotNull(map);
    }

    @Override
    public boolean exists(final K key) {
        return map.containsKey(key);
    }

    @Override
    public V get(final K key) {
        return map.get(key);
    }

    @Override
    public void put(final K key, final V value) {
        map.put(key, value);
    }
}
