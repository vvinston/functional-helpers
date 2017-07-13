package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class PredicateBiFunction<INPUT, RESULT> implements BiFunction<INPUT, RESULT, Boolean> {

    private final BiPredicate<INPUT, RESULT> predicate;

    PredicateBiFunction(@Nonnull final BiPredicate<INPUT, RESULT> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    @Override
    public Boolean apply(@Nullable  final INPUT input1, @Nullable final RESULT input2) {
        return predicate.test(input1, input2);
    }
}
