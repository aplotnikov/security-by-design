package io.github.aplotnikov.domain.v2;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.isNull;
import static io.vavr.control.Validation.invalid;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Objects;
import java.util.function.Predicate;

import io.vavr.collection.List;
import io.vavr.control.Validation;

final class PersonalId {

    private final String id;

    private PersonalId(String id) {
        this.id = id;
    }

    static PersonalId of(String value) {
        Validation<IllegalArgumentException, PersonalId> id = validate(value)
            .map(PersonalId::new)
            .mapError(IllegalArgumentException::new);

        if (id.isInvalid()) {
            throw id.getError();
        }

        return id.get();
    }

    private static Validation<String, String> validate(String value) {
        return Match(value).of(
            Case($(isNull()), id -> invalid("Personal ID can not be null")),
            Case($(String::isBlank), id -> invalid("Personal ID can not be empty or blank")),
            Case($(id -> id.length() != 11), id -> invalid("Personal ID can not contain more or less than 11 symbols")),
            Case($(id -> !id.matches("[0-9]+")), id -> invalid("Personal ID can not contain anything except digits")),
            Case($(validCheckSum().negate()), id -> invalid("Personal ID does not pass check sum validation")),
            Case($(), Validation::valid)
        );
    }

    private static Predicate<String> validCheckSum() {
        return personalId -> {
            int modulo = sum(personalId) % 10;
            int lastDigit = Character.getNumericValue(personalId.charAt(personalId.length() - 1));
            return modulo == 0 && lastDigit == 0 || lastDigit == 10 - modulo;
        };
    }

    private static int sum(String personalId) {
        return List.of(personalId.split(EMPTY))
            .dropRight(1)
            .map(Integer::parseInt)
            .zipWithIndex()
            .foldLeft(
                0,
                (sum, element) -> sum + element._1 * getMultiplier(element._2 + 1)
            );
    }

    private static int getMultiplier(int number) {
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

    public String getValue() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        PersonalId that = (PersonalId) other;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonalId{id='" + id + "\'}";
    }
}
