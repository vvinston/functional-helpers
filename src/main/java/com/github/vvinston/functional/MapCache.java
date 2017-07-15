package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

final class MapCache<KEY, VALUE> implements Cache<KEY, VALUE> {
    private final Map<KEY, VALUE> map;

    MapCache(@Nonnull final Map<KEY, VALUE> map) {
        this.map = Objects.requireNonNull(map);
    }

    @Override
    public boolean exists(@Nullable final KEY key) {
        return map.containsKey(key);
    }

    @Override
    public VALUE get(@Nullable final KEY key) {
        return map.get(key);
    }

    @Override
    public void put(@Nullable final KEY key, @Nullable final VALUE value) {
        map.put(key, value);
    }
}
