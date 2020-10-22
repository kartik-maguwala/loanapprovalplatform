package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationRequest;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationResponse;
import com.socgen.loanapprovalplatform.service.CarLoanApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
@Transactional
public class CarLoanApplicationController {
    private final Logger log = LoggerFactory.getLogger(CarLoanApplicationController.class);

    private CarLoanApplicationService carLoanApplicationService;

    public CarLoanApplicationController(CarLoanApplicationService carLoanApplicationService) {
        this.carLoanApplicationService = carLoanApplicationService;
    }

    @PostMapping("/carloan")
    @ResponseStatus(HttpStatus.CREATED)
    public CarLoanApplicationResponse applyLoan(@Valid @RequestBody CarLoanApplicationRequest request) {

        log.debug("REST request to apply CarLoanApplication : {}", request);

        CarLoanApplication carLoanApplication = request.toModel();
        return carLoanApplicationService.applyLoan(carLoanApplication).toResponseDto();
    }



}
