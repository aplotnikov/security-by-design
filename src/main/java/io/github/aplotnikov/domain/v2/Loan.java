package io.github.aplotnikov.domain.v2;

final class Loan {

    private final LoanId id;

    private final Money amount;

    private final int term;

    private final ClientId clientId;

    Loan(LoanId id, Money amount, int term, ClientId clientId) {
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

    int getTerm() {
        return term;
    }

    ClientId getClientId() {
        return clientId;
    }
}
