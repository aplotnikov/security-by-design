package io.github.aplotnikov.domain.v2;

import static io.github.aplotnikov.domain.v2.PersonalIdValidator.Violation.violation;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.isNull;
import static io.vavr.control.Validation.invalid;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.function.Function;
import java.util.function.Predicate;

import io.vavr.collection.List;
import io.vavr.control.Validation;

final class PersonalIdValidator {

    static class Violation {

        private final String message;

        private Violation(String message) {
            this.message = requireNonNull(message, "Violation message can not be null");
        }

        static Violation violation(String message) {
            return new Violation(message);
        }

        <T> T to(Function<String, T> transformer) {
            return transformer.apply(message);
        }

        @Override
        public String toString() {
            return "Violation{message='" + message + "\'}";
        }
    }

    Validation<Violation, String> validate(String value) {
        return Match(value).of(
            Case($(isNull()), id -> invalid(violation("Personal ID can not be null"))),
            Case($(String::isBlank), id -> invalid(violation("Personal ID can not be empty or blank"))),
            Case($(id -> id.length() != 11), id -> invalid(violation("Personal ID can not contain more or less than 11 symbols"))),
            Case($(id -> !id.matches("[0-9]+")), id -> invalid(violation("Personal ID can not contain anything except digits"))),
            Case($(validCheckSum().negate()), id -> invalid(violation("Personal ID does not pass check sum validation"))),
            Case($(), Validation::valid)
        );
    }

    private Predicate<String> validCheckSum() {
        return personalId -> {
            int modulo = sum(personalId) % 10;
            int lastDigit = Character.getNumericValue(personalId.charAt(personalId.length() - 1));
            return modulo == 0 && lastDigit == 0 || lastDigit == 10 - modulo;
        };
    }

    private int sum(String personalId) {
        return List.of(personalId.split(EMPTY))
            .dropRight(1)
            .map(Integer::parseInt)
            .zipWithIndex()
            .foldLeft(
                0,
                (sum, element) -> sum + element._1 * getMultiplier(element._2 + 1)
            );
    }

    private int getMultiplier(int number) {
        return Match(number % 4).of(
            Case($(1), value -> 1),
            Case($(2), value -> 3),
            Case($(3), value -> 7),
            Case($(0), value -> 9),
            Case($(), value -> {
                throw new IllegalArgumentException("There is no such multiplier - " + value);
            })
        );
    }
}
