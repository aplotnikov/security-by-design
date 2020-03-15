package io.github.aplotnikov.v1.domain;

import java.util.Objects;

public class Client {

    private Long id;

    private String firstName;

    private String secondName;

    private String password;

    private String personalId;

    public Client() {
    }

    public Client(Long id, String firstName, String secondName, String password, String personalId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.personalId = personalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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
            Objects.equals(firstName, client.firstName) &&
            Objects.equals(secondName, client.secondName) &&
            Objects.equals(password, client.password) &&
            Objects.equals(personalId, client.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, password, personalId);
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", password='" + password + '\'' +
            ", personalId='" + personalId + '\'' +
            '}';
    }
}
