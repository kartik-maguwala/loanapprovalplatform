package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.repository.CarLoanApplicationRepository;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.service.FrontDeskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/fd")
@Transactional
public class FrontDeskController {

    private final Logger log = LoggerFactory.getLogger(FrontDeskController.class);

    private FrontDeskService frontDeskService;
    private LoanFrontDeskRepository loanFrontDeskRepository;

    public FrontDeskController(FrontDeskService frontDeskService,
                               LoanFrontDeskRepository loanFrontDeskRepository) {
        this.frontDeskService = frontDeskService;
        this.loanFrontDeskRepository = loanFrontDeskRepository;
    }

    @PutMapping("/{applicationid}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approve(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid FrontDeskApproveRequest request) {

        LoanFrontDesk loanFrontDesk = loanFrontDeskRepository.findByCarLoanApplicationId(applicationid)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });

        frontDeskService.approve(loanFrontDesk, request);
    }
}
