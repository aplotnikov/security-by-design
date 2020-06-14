package io.github.aplotnikov.v1.infrastructure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.aplotnikov.v1.domain.LoanService;

@RestController
public class LoanController {

    private final LoanService service;

    LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping("loans")
    public void applyForLoan(@RequestBody LoanDto loan) {
        service.processLoan(loan.getClientId(), loan.getAmount(), loan.getTerm());
    }

}
