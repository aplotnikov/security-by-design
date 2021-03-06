package io.github.aplotnikov.v2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.vavr.control.Validation;

final class PersonalId {

    private final String id;

    private PersonalId(String id) {
        this.id = id;
    }

    static PersonalId of(String value) {
        Validation<IllegalArgumentException, PersonalId> validationResult = new PersonalIdValidator()
            .validate(value)
            .map(PersonalId::new)
            .mapError(violation -> violation.to(IllegalArgumentException::new));

        if (validationResult.isInvalid()) {
            throw validationResult.getError();
        }

        return validationResult.get();
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

        return new EqualsBuilder()
            .append(id, that.id)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(id)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "PersonalId{id='" + id + "\'}";
    }
}
