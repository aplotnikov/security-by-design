package io.github.aplotnikov.domain.v1;

import static java.lang.invoke.MethodHandles.lookup;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoanService {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final RiskService riskService;

    private final LoanRepository repository;

    LoanService(RiskService riskService, LoanRepository repository) {
        this.riskService = riskService;
        this.repository = repository;
    }

    void processLoan(Client client, BigDecimal amount, int term) {
        LOG.info("Client {} is taking a loan with amount {} and term {}", client, amount, term);

        RiskCheckResult result = riskService.check(client);
        if (result.isRisky()) {
            throw new IllegalStateException("Client can not take a loan as it is too risky");
        }

        Loan loan = new Loan();
        loan.setClientId(client.getId());
        loan.setAmount(amount);
        loan.setTerm(term);

        repository.save(loan);
    }
}
