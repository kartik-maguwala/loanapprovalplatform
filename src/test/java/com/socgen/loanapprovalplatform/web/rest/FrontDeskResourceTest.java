package com.socgen.loanapprovalplatform.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.LoanFrontDesk;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanType;
import com.socgen.loanapprovalplatform.dto.FrontDeskApproveRequest;
import com.socgen.loanapprovalplatform.repository.LoanFrontDeskRepository;
import com.socgen.loanapprovalplatform.service.CarLoanApplicationService;
import com.socgen.loanapprovalplatform.service.FrontDeskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FrontDeskResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FrontDeskService frontDeskService;
    @MockBean
    private LoanFrontDeskRepository loanFrontDeskRepository;
    @MockBean
    private CarLoanApplicationService carLoanApplicationService;


    @Test
    public void When_ApproveByFrontDesk_Expect_Success() throws Exception {

        FrontDeskApproveRequest request = new FrontDeskApproveRequest("Dummy Notes");

        CarLoanApplication carLoanApplication = new CarLoanApplication()
                .firstname("firstname")
                .lastname("lastname")
                .email("abc@example.com")
                .loantype(LoanType.CAR)
                .amount(1234)
                .pancardno("abcd")
                .accountno("1234566789")
                .ifsccode("ICIC1234")
                .bankname("DUMMYBANK")
                .address1("Mumbai")
                .address2("");
        carLoanApplication.setId(123L);

        LoanFrontDesk loanFrontDesk = new LoanFrontDesk().notes("Dummy Notes");
        loanFrontDesk.setId(1L);

        doNothing().when(frontDeskService).approve(any(LoanFrontDesk.class), any(FrontDeskApproveRequest.class));

        when(loanFrontDeskRepository.findByCarLoanApplication_IdAndStatus(123L, LoanFrontDeskStatus.PENDING)).thenReturn(Optional.of(loanFrontDesk));

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/api/fd/123/approve")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
