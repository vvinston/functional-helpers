package com.github.vvinston.functional;

import java.util.Map;

public class MapCache<K, V> implements Cache<K, V> {

    private final Map<K, V> map;

    public MapCache(final Map<K, V> map) {
        this.map = map;
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
