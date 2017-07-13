package com.github.vvinston.functional;

@SuppressWarnings("WeakerAccess")
public interface Cache<KEY, VALUE> {

    boolean exists(KEY key);

    VALUE get(KEY key);

    void put(KEY key, VALUE value);
}
