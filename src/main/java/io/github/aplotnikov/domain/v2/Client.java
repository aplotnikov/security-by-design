package io.github.aplotnikov.domain.v2;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

final class Client {

    private final ClientId id;

    private final FirstName firstName;

    private final SecondName secondName;

    private final Password password;

    private final PersonalId personalId;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Client client = (Client) other;
        return Objects.equals(id, client.id) &&
            Objects.equals(personalId, client.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId);
    }

    @Override
    public String toString() {
        return "Client{id=" + id + '}';
    }
}
