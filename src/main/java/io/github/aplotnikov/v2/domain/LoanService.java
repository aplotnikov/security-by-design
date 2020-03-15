package io.github.aplotnikov.v2.domain;

import static java.lang.invoke.MethodHandles.lookup;

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
    public LoanId process(Loan loan) {
        LOG.info("Client {} is taking a loan with amount {} and term {}", loan.getClientId(), loan.getAmount(), loan.getTerm());

        Client client = clientRepository.findById(loan.getClientId());
        RiskCheckResult result = riskService.check(client);

        if (result.isRisky()) {
            throw new IllegalStateException("Client can not take a loan as it is too risky");
        }

        loanRepository.save(loan);

        return loan.getId();
    }

    @Transactional(readOnly = true)
    public Loan findBy(LoanId id) {
        return loanRepository.findById(id);
    }
}
