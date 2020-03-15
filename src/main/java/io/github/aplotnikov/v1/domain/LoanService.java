package io.github.aplotnikov.v1.domain;

import static java.lang.invoke.MethodHandles.lookup;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class LoanService {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final RiskService riskService;

    private final LoanRepository loanRepository;

    private final ClientRepository clientRepository;

    public LoanService(RiskService riskService, LoanRepository loanRepository, ClientRepository clientRepository) {
        this.riskService = riskService;
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void processLoan(Long clientId, BigDecimal amount, int term) {
        LOG.info("Client {} is taking a loan with amount {} and term {}", clientId, amount, term);

        Client client = clientRepository.findById(clientId);
        RiskCheckResult result = riskService.check(client);

        if (result.isRisky()) {
            throw new IllegalStateException("Client can not take a loan as it is too risky");
        }

        Loan loan = new Loan();
        loan.setClientId(client.getId());
        loan.setAmount(amount);
        loan.setTerm(term);

        loanRepository.save(loan);
    }
}
