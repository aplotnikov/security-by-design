package io.github.aplotnikov.v2.infrastructure;

import static io.github.aplotnikov.v2.domain.Money.Currency.PLN;
import static java.lang.String.format;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.UUID.randomUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.status;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.aplotnikov.v2.domain.ClientId;
import io.github.aplotnikov.v2.domain.Loan;
import io.github.aplotnikov.v2.domain.LoanId;
import io.github.aplotnikov.v2.domain.LoanService;
import io.github.aplotnikov.v2.domain.Money;
import io.github.aplotnikov.v2.domain.Term;
import io.github.aplotnikov.v2.domain.exceptions.ValidationException;

@RestController
@RequestMapping(
    path = "loans",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
)
public class LoanController {

    private static final Logger LOG = LoggerFactory.getLogger(lookup().lookupClass());

    private final LoanService service;

    LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> applyForLoan(@Valid @RequestBody LoanDto loan) {
        try {
            LoanId id = service.process(toDomain(loan));
            return created(
                linkTo(methodOn(LoanController.class).findBy(id.getValue())).toUri()
            ).build();
        } catch (ValidationException exception) {
            LOG.info("Incorrect argument was passed and the loan was rejected");
            return badRequest().body(exception.getMessage());
        } catch (Exception exception) {
            String uuid = randomUUID().toString();
            LOG.error(
                format("Application threw following exception with UUID %s: ", uuid),
                exception
            );

            return status(INTERNAL_SERVER_ERROR)
                .body("Internal application error happened. Contact your support team. Error uuid is " + uuid);
        }
    }

    @GetMapping("{id}")
    public LoanDto findBy(@PathVariable long id) {
        Loan loan = service.findBy(LoanId.of(id));
        return new LoanDto(
            loan.getClientId().getValue(),
            loan.getAmount().getAmount(),
            loan.getTerm().getValue()
        );
    }

    private Loan toDomain(LoanDto dto) {
        try {
            return new Loan(
                Money.of(dto.getAmount(), PLN),
                Term.of(dto.getTerm()),
                ClientId.of(dto.getClientId())
            );
        } catch (Exception exception) {
            throw new ValidationException(exception.getMessage(), exception);
        }
    }

}
