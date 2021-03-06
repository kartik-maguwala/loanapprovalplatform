package com.socgen.loanapprovalplatform.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CarLoanDisburseRequest {

    @Min(value = 1)
    private int disburseAmount;

    @NotBlank(message = "Please enter transaction id")
    private String transactionId;

    @NotBlank(message = "Please enter branch ifsc code")
    @Size(max=20, message="Name should have maximum 20 characters")
    private String branchIfsc;

    @NotNull(message = "Please enter disbursal date")
    private Date disbursementDate;

    public CarLoanDisburseRequest() {
        disbursementDate = new Date();
    }

    public CarLoanDisburseRequest(int disburseAmount, String transactionId, String branchIfsc, Date disbursementDate) {
        this.disburseAmount = disburseAmount;
        this.transactionId = transactionId;
        this.branchIfsc = branchIfsc;
        this.disbursementDate = disbursementDate;
    }

    public int getDisburseAmount() {
        return disburseAmount;
    }

    public void setDisburseAmount(int disburseAmount) {
        this.disburseAmount = disburseAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBranchIfsc() {
        return branchIfsc;
    }

    public void setBranchIfsc(String branchIfsc) {
        this.branchIfsc = branchIfsc;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    @Override
    public String toString() {
        return "CarLoanDisburseRequest{" +
                "disburseAmount=" + disburseAmount +
                ", transactionId='" + transactionId + '\'' +
                ", branchIfsc='" + branchIfsc + '\'' +
                ", disbursementDate=" + disbursementDate +
                '}';
    }
}
