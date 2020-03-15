package io.github.aplotnikov.v2.infrastructure;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanDto {

    private final long clientId;

    @NotEmpty
    private final BigDecimal amount;

    private final byte term;

    @JsonCreator
    public LoanDto(
        @JsonProperty("clientId") long clientId,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("term") byte term
    ) {
        this.clientId = clientId;
        this.amount = amount;
        this.term = term;
    }

    public long getClientId() {
        return clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public byte getTerm() {
        return term;
    }
}
