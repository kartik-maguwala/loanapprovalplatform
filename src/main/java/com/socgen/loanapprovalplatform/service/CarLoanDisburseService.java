package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.CarLoanDisburseInfo;
import com.socgen.loanapprovalplatform.dto.CarLoanDisburseRequest;

public interface CarLoanDisburseService {

    void disburse(CarLoanDisburseInfo carLoanDisburseInfo, CarLoanDisburseRequest request);
}
