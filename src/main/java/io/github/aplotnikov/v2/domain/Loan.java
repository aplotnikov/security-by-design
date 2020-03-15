package io.github.aplotnikov.v2.domain;

import static java.util.Objects.requireNonNull;

public final class Loan {

    private final LoanId id;

    private final Money amount;

    private final Term term;

    private final ClientId clientId;

    public Loan(Money amount, Term term, ClientId clientId) {
        this(LoanId.TO_REPLACE, amount, term, clientId);
    }

    Loan(LoanId id, Money amount, Term term, ClientId clientId) {
        this.id = requireNonNull(id, "Loan id can not be null");
        this.amount = requireNonNull(amount, "Loan amount can not be null");
        this.term = requireNonNull(term, "Loan term can not be null");
        this.clientId = requireNonNull(clientId, "Client id can not be null");
    }

    public LoanId getId() {
        return id;
    }

    public Money getAmount() {
        return amount;
    }

    public Term getTerm() {
        return term;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
