package io.github.aplotnikov.v2.domain;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final class Client {

    private final ClientId id;

    private final FirstName firstName;

    private final SecondName secondName;

    private final Password password;

    private final PersonalId personalId;

    private final List<Loan> loans = new ArrayList<>();

    Client(ClientId id, FirstName firstName, SecondName secondName, Password password, PersonalId personalId) {
        this.id = requireNonNull(id, "Client id can not be null");
        this.firstName = requireNonNull(firstName, "First name can not be null");
        this.secondName = requireNonNull(secondName, "Second name can not be null");
        this.password = requireNonNull(password, "Password can not be null");
        this.personalId = requireNonNull(personalId, "Personal id can not be null");
    }

    ClientId getId() {
        return id;
    }

    FirstName getFirstName() {
        return firstName;
    }

    SecondName getSecondName() {
        return secondName;
    }

    Password getPassword() {
        return password;
    }

    PersonalId getPersonalId() {
        return personalId;
    }

    void takeLoan(Money amount, Term term) {
        notNull(amount, "Amount can not be null");
        notNull(term, "Term can not be null");

        loans.add(new Loan(amount, term, id));
    }

    List<Loan> getLoans() {
        return List.copyOf(loans);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Client client = (Client) other;

        return new EqualsBuilder()
            .append(personalId, client.personalId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(personalId)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Client{id=" + id + '}';
    }
}
