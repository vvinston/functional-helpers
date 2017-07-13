package com.github.vvinston.functional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ConditionalFunctionBuilderStepThree<INPUT, RESULT> {

    private final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases;
    private final Predicate<INPUT> predicate;

    ConditionalFunctionBuilderStepThree(
            @Nonnull final Predicate<INPUT> predicate,
            @Nonnull final List<Tuple<Predicate<INPUT>, Function<INPUT, RESULT>>> cases) {
        this.predicate = Objects.requireNonNull(predicate);
        this.cases = Objects.requireNonNull(cases);
    }

    @SuppressWarnings("PMD.AccessorClassGeneration")
    public ConditionalFunctionBuilderStepTwo<INPUT, RESULT> then(@Nonnull final Function<INPUT, RESULT> success) {
        cases.add(SimpleTuple.of(predicate, Objects.requireNonNull(success)));
        return new ConditionalFunctionBuilderStepTwo<>(cases);
    }
}
