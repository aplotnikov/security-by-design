package io.github.aplotnikov.v2.domain;

interface LoanRepository {

    void save(Loan loan);

    Loan findById(LoanId id);

}
