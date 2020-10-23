package com.socgen.loanapprovalplatform.dto;

public class CarLoanComplianceRequest {
    private String notes;

    public CarLoanComplianceRequest() {
    }

    public CarLoanComplianceRequest(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CarLoanComplianceRequest{" +
                "notes='" + notes + '\'' +
                '}';
    }
}
