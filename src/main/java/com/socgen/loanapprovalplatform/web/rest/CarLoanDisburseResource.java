package com.socgen.loanapprovalplatform.web.rest;

import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationDetailedResponse;
import com.socgen.loanapprovalplatform.dto.CarLoanDisburseRequest;
import com.socgen.loanapprovalplatform.exception.ApplicationNotFoundException;
import com.socgen.loanapprovalplatform.repository.CarLoanDisburseInfoRepository;
import com.socgen.loanapprovalplatform.service.CarLoanDisburseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/cld")
@Transactional
public class CarLoanDisburseResource {

    private final Logger log = LoggerFactory.getLogger(CarLoanDisburseResource.class);

    private CarLoanDisburseInfoRepository carLoanDisburseInfoRepository;
    private CarLoanDisburseService carLoanDisburseService;


    public CarLoanDisburseResource(CarLoanDisburseInfoRepository carLoanDisburseInfoRepository,
                                   CarLoanDisburseService carLoanDisburseService) {
        this.carLoanDisburseInfoRepository = carLoanDisburseInfoRepository;
        this.carLoanDisburseService = carLoanDisburseService;
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_DISBURSAL"})
    @PutMapping("/{applicationid}/disburse")
    @ResponseStatus(HttpStatus.OK)
    public void disburse(@PathVariable("applicationid") Long applicationid, @RequestBody @Valid CarLoanDisburseRequest request) {

        log.debug("Car disbursal dept REST request to disburse CarLoanApplication : {}", request);
        CarLoanDisburseInfo carLoanDisburseInfo = carLoanDisburseInfoRepository
                .findByCarLoanApplication_IdAndStatus(applicationid, CarLoanDisburseStatus.PENDING)
                .orElseThrow(() -> {
                    return new ApplicationNotFoundException("id-" + applicationid);
                });

        carLoanDisburseService.disburse(carLoanDisburseInfo, request);
    }

    @RolesAllowed(value = {"ROLE_ADMIN", "ROLE_DISBURSAL"})
    @GetMapping("/pending/{pageNo}/{pageSize}")
    public List<CarLoanApplicationDetailedResponse> getPendingCarLoanApplication(@PathVariable("pageNo") int pageNo,
                                                                                 @PathVariable("pageSize") int pageSize) {

        log.debug("Car disbursal REST request to get pending car loan application with pageno-{} and pagesize-{}", pageNo, pageSize);
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<CarLoanDisburseInfo> carLoanDisburseInfos = carLoanDisburseInfoRepository.findAllByStatus(CarLoanDisburseStatus.PENDING,
                paging);

        return carLoanDisburseInfos.stream()
                .map(carLoanDisburseInfo -> carLoanDisburseInfo.getCarLoanApplication().toDetailedResponseDto())
                .collect(Collectors.toList());

    }

}
