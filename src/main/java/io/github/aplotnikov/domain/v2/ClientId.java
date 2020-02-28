package io.github.aplotnikov.domain.v2;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.Long.parseLong;

import java.util.Objects;

final class ClientId {

    private final long id;

    private ClientId(long id) {
        this.id = id;
    }

    static ClientId of(String id) {
        checkNotNull(id, "Client id can not be null");
        checkState(id.length() <= 10, "Client id can not contain more than 10 symbols");
        checkState(id.matches("[0-9]+"), "Client id can not contain anything except numbers");
        return of(parseLong(id));
    }

    static ClientId of(long id) {
        checkState(id > 0, "Client id has to be more than zero");
        checkState(id <= 1_000_000_000, "Client id has to be less than 1 000 000 000");
        return new ClientId(id);
    }

    long getValue() {
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

        ClientId clientId = (ClientId) other;
        return id == clientId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ClientId{id=" + id + '}';
    }
}
