package com.socgen.loanapprovalplatform.service;

import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;

public interface FrontDeskService {
    void approve(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request);

    void reject(LoanFrontDesk loanFrontDesk, FrontDeskApproveRequest request);
}
