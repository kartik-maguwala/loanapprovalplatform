package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanDisburseRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanDisburseInfoRepository;
import com.socgen.loanapprovalplatform.service.CarLoanDisburseService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/cld")
@Transactional
public class CarLoanDisburseResource {

    private CarLoanDisburseInfoRepository carLoanDisburseInfoRepository;
    private CarLoanDisburseService carLoanDisburseService;


    public CarLoanDisburseResource(CarLoanDisburseInfoRepository carLoanDisburseInfoRepository,
                                   CarLoanDisburseService carLoanDisburseService) {
        this.carLoanDisburseInfoRepository = carLoanDisburseInfoRepository;
        this.carLoanDisburseService = carLoanDisburseService;
    }

    @PutMapping("/{applicationid}/disburse")
    @ResponseStatus(HttpStatus.OK)
    public void disburse(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid CarLoanDisburseRequest request) {

        CarLoanDisburseInfo carLoanDisburseInfo = carLoanDisburseInfoRepository
                .findByCarLoanApplicationIdAndStatus(applicationid, CarLoanDisburseStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        carLoanDisburseService.disburse(carLoanDisburseInfo, request);
    }

}
