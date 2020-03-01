package io.github.aplotnikov.domain.v2;

final class Loan {

    private final LoanId id;

    private final Money amount;

    private final Term term;

    private final ClientId clientId;

    Loan(Money amount, Term term, ClientId clientId) {
        this(LoanId.TO_REPLACE, amount, term, clientId);
    }

    Loan(LoanId id, Money amount, Term term, ClientId clientId) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.clientId = clientId;
    }

    LoanId getId() {
        return id;
    }

    Money getAmount() {
        return amount;
    }

    Term getTerm() {
        return term;
    }

    ClientId getClientId() {
        return clientId;
    }
}
