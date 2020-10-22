package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationRequest;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationResponse;
import com.socgen.loanapprovalplatform.service.CarLoanApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/api/carloan")
@Transactional
public class CarLoanApplicationResource {
    private final Logger log = LoggerFactory.getLogger(CarLoanApplicationResource.class);

    private CarLoanApplicationService carLoanApplicationService;

    public CarLoanApplicationResource(CarLoanApplicationService carLoanApplicationService) {
        this.carLoanApplicationService = carLoanApplicationService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CarLoanApplicationResponse applyLoan(@Valid @RequestBody CarLoanApplicationRequest request) {

        log.debug("REST request to apply CarLoanApplication : {}", request);

        CarLoanApplication carLoanApplication = request.toModel();
        return carLoanApplicationService.applyLoan(carLoanApplication).toResponseDto();
    }
}
