package com.socgen.loanapprovalplatform.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationRequest;
import com.socgen.loanapprovalplatform.service.CarLoanApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CarLoanApplicationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CarLoanApplicationService carLoanApplicationService;

    @Test
    public void When_ApplyCarLoanWithRightInfo_Expect_Success() throws Exception {

        CarLoanApplicationRequest request = new CarLoanApplicationRequest(
                "firstname", "lastname", "test@exmaple.com", "CAR", 1000,
                "AAAAAA", "123456789", "DUMMY1234", "DUMMYBANK",
                "32,ABc,XYZ", "");

        CarLoanApplication carLoanApplication = request.toModel();
        carLoanApplication.setId(123L);

        when(carLoanApplicationService.applyLoan(any(CarLoanApplication.class))).thenReturn(carLoanApplication);


        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/carloan")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.applicationId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
