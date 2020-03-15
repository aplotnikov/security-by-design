package io.github.aplotnikov.v2.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.Long.parseLong;

import java.util.Objects;

public class LoanId {

    private static final long FAKE_VALUE = -1;

    static final LoanId TO_REPLACE = new LoanId(FAKE_VALUE);

    private final long id;

    private LoanId(long id) {
        this.id = id;
    }

    public static LoanId of(String id) {
        checkNotNull(id, "Loan id can not be null");
        checkState(id.length() <= 10, "Loan id can not contain more than 10 symbols");
        checkState(id.matches("[0-9]+"), "Loan id can not contain anything except digits");
        return of(parseLong(id));
    }

    public static LoanId of(long id) {
        checkState(id > 0, "Loan id has to be more than zero");
        checkState(id <= 2_000_000_000, "Loan id has to be less than 2 000 000 000");
        return new LoanId(id);
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

        LoanId loanId = (LoanId) other;
        return id == loanId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LoanId{id=" + id + '}';
    }
}
