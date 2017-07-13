package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepTwo<INPUT, RESULT> {

    private final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases;

    ConditionalFunctionBuilderStepTwo(@Nonnull final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases) {
        this.cases = Objects.requireNonNull(cases);
    }

    public ConditionalFunctionBuilderStepThree<INPUT, RESULT> when(@Nonnull final Predicate<INPUT> predicate) {
        return new ConditionalFunctionBuilderStepThree<>(Objects.requireNonNull(predicate), cases);
    }

    public Function<INPUT, RESULT> otherwise(@Nonnull final Function<INPUT, RESULT> otherwise) {
        return new ConditionalFunction<>(cases, Objects.requireNonNull(otherwise));
    }
}
