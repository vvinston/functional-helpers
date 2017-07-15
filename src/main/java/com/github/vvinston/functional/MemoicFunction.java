package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class MemoicFunction<INPUT, RESULT> implements Function<INPUT, RESULT> {
    private final Map<INPUT, RESULT> map;
    private final Function<INPUT, RESULT> function;

    public MemoicFunction(@Nonnull final Function<INPUT, RESULT> function, @Nonnull final Map<INPUT, RESULT> map) {
        this.map = Objects.requireNonNull(map);
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public RESULT apply(@Nullable final INPUT input) {
        return map.computeIfAbsent(input, function);
    }
}
