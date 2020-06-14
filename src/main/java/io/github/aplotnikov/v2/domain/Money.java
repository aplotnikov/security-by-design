package io.github.aplotnikov.v2.domain;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.validState;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Money {

    public enum Currency {
        EUR,
        PLN
    }

    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(50_000);

    private final BigDecimal amount;

    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(String amount, Currency currency) {
        notNull(amount, "Amount can not be null");
        validState(!amount.isBlank(), "Amount can not empty or blank");
        validState(amount.length() <= 8, "Amount can not be longer than 8 symbols");
        validState(amount.matches("^\\d{1,5}(\\.\\d{1,2})?"), "Amount has to match money format e.g. 10000.01");

        return of(new BigDecimal(amount), currency);
    }

    public static Money of(BigDecimal amount, Currency currency) {
        notNull(amount, "Amount can not be null");
        notNull(currency, "Currency can not be null");
        validState(amount.precision() <= 7, "Amount can not be longer than 8 symbols");
        validState(amount.scale() <= 2, "Amount scale can not be more than 2");
        validState(amount.compareTo(ZERO) >= 0, "Amount can not be less than 0.00");
        validState(amount.compareTo(MAX_AMOUNT) <= 0, "Amount can not be more than 50 000");

        return new Money(amount, currency);
    }

    public Money add(Money other) {
        validState(
            currency == other.currency,
            "It is impossible to add money in different currencies"
        );
        return new Money(
            amount.add(other.amount),
            currency
        );
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Money money = (Money) other;

        return new EqualsBuilder()
            .append(amount, money.amount)
            .append(currency, money.currency)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(amount)
            .append(currency)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Money{" +
            "amount=" + amount +
            ", currency=" + currency +
            '}';
    }
}
