package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.function.Function;

public final class MemoicFunctionBuilder {
    public <INPUT, RESULT> Function<INPUT, RESULT> memoic(@Nonnull final Function<INPUT, RESULT> function) {
        return new MemoicFunction<>(function, new HashMap<>());
    }
}
