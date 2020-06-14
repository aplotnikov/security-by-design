package io.github.aplotnikov.v2.domain

import static io.github.aplotnikov.v2.domain.Money.Currency.EUR
import static io.github.aplotnikov.v2.domain.Money.Currency.PLN

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class MoneySpec extends Specification {

    void 'should be impossible to create a money when amount is null'() {
        when:
            Money.of(null, EUR)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Amount can not be null'
    }

    void 'should be impossible to create a money when currency is null'() {
        when:
            Money.of(0.00, null)
        then:
            NullPointerException exception = thrown()
            exception.message == 'Currency can not be null'
    }

    void 'should be impossible to create a money when amount is less than 1 cent'() {
        when:
            Money.of(value, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount can not be less than 0.00'
        where:
            value << [
                -1.0,
                -0.01
            ]
    }

    void 'should be impossible to create a money when amount is more than 50 000'() {
        when:
            Money.of(50_001.00, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount can not be more than 50 000'
    }

    void 'should be impossible to create a money when amount is empty or blank'() {
        when:
            Money.of(value, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount can not empty or blank'
        where:
            value << [
                '',
                ' ',
                ' '.repeat(5)
            ]
    }

    void 'should be impossible to create a money when amount is longer than 8 symbols'() {
        when:
            Money.of(value, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount can not be longer than 8 symbols'
        where:
            value << [
                '1'.repeat(9),
                100_000.01
            ]
    }

    void 'should be impossible to create a money when amount does not have precision 2'() {
        when:
            Money.of(value, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount scale can not be more than 2'
        where:
            value << [
                1.001,
                1.0001
            ]
    }

    void 'should be impossible to create a money when amount does not match money format'() {
        when:
            Money.of(value, EUR)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'Amount has to match money format e.g. 10000.01'
        where:
            value << [
                'A',
                '!',
                '-1',
                '#',
                '1.001',
                '1.00!',
                '!1.00',
                '1,00',
                '1,00.00'
            ]
    }

    void 'should be possible to create a money'() {
        when:
            Money money = Money.of(value, EUR)
        then:
            with(money) {
                amount == expectedAmount
                currency == EUR
            }
        where:
            value  || expectedAmount
            0      || 0.00
            1      || 1.00
            1.0    || 1.00
            1.00   || 1.00
            '1'    || 1.00
            '1.0'  || 1.00
            '1.00' || 1.00
            '1000' || 1000.00
    }

    void 'should be impossible to add money with different currencies'() {
        given:
            Money oneEuro = Money.of(1.0, EUR)
        and:
            Money onePln = Money.of(1.0, PLN)
        when:
            oneEuro.add(onePln)
        then:
            IllegalStateException exception = thrown()
            exception.message == 'It is impossible to add money in different currencies'
    }

    void 'should be possible to add money with the same currency'() {
        given:
            Money tenPln = Money.of(10.0, PLN)
        and:
            Money onePln = Money.of(1.0, PLN)
        when:
            Money result = tenPln.add(onePln)
        then:
            with(result) {
                amount == 11.00
                currency == PLN
            }
    }

    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(Money)
                .usingGetClass()
                .verify()
    }
}
