package io.github.aplotnikov.v2.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.Long.parseLong;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ClientId {

    private static final long FAKE_VALUE = -1;

    static final ClientId TO_REPLACE = new ClientId(FAKE_VALUE);

    private final long id;

    private ClientId(long id) {
        this.id = id;
    }

    public static ClientId of(String id) {
        checkNotNull(id, "Client id can not be null");
        checkState(id.length() <= 10, "Client id can not contain more than 10 symbols");
        checkState(id.matches("[0-9]+"), "Client id can not contain anything except digits");
        return of(parseLong(id));
    }

    public static ClientId of(long id) {
        checkState(id > 0, "Client id has to be more than zero");
        checkState(id <= 1_000_000_000, "Client id has to be less than 1 000 000 000");
        return new ClientId(id);
    }

    public long getValue() {
        return id;
    }

    public boolean isForReplace() {
        return id == FAKE_VALUE;
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

        return new EqualsBuilder()
            .append(id, clientId.id)
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
        return "ClientId{id=" + id + '}';
    }
}
