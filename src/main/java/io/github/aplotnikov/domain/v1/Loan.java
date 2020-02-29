package io.github.aplotnikov.domain.v1;

import java.math.BigDecimal;
import java.util.Objects;

class Loan {

    private Long id;

    private BigDecimal amount;

    private int term;

    private Long clientId;

    Loan() {
    }

    Loan(Long id, BigDecimal amount, int term, Long clientId) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.clientId = clientId;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    BigDecimal getAmount() {
        return amount;
    }

    void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    int getTerm() {
        return term;
    }

    void setTerm(int term) {
        this.term = term;
    }

    Long getClientId() {
        return clientId;
    }

    void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Loan loan = (Loan) other;
        return term == loan.term &&
            Objects.equals(id, loan.id) &&
            Objects.equals(amount, loan.amount) &&
            Objects.equals(clientId, loan.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, term, clientId);
    }

    @Override
    public String toString() {
        return "Loan{" +
            "id=" + id +
            ", amount=" + amount +
            ", term=" + term +
            ", clientId=" + clientId +
            '}';
    }
}
