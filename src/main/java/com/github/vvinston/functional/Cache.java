package com.github.vvinston.functional;

@SuppressWarnings("WeakerAccess")
public interface Cache<K, V> {

    boolean exists(K key);

    V get(K key);

    void put(K key, V value);
}
