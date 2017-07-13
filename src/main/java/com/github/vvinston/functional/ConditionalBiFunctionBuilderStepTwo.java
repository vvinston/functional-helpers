package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public final class ConditionalBiFunctionBuilderStepTwo<INPUT1, INPUT2, RESULT> {

    private final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases;

    ConditionalBiFunctionBuilderStepTwo(@Nonnull final List<Tuple<BiPredicate<INPUT1, INPUT2>, BiFunction<INPUT1, INPUT2, RESULT>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalBiFunctionBuilderStepThree<INPUT1, INPUT2, RESULT> when(@Nonnull final BiPredicate<INPUT1, INPUT2> predicate) {
        return new ConditionalBiFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
    }

    public BiFunction<INPUT1, INPUT2, RESULT> otherwise(@Nonnull final BiFunction<INPUT1, INPUT2, RESULT> otherwise) {
        return new ConditionalBiFunction<>(cases, Objects.requireNonNull(otherwise));
    }
}
