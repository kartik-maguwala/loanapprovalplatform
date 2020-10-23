package com.socgen.loanapprovalplatform.dto;

public class RiskComplianceRequest {
    private String notes;

    public RiskComplianceRequest() {
    }

    public RiskComplianceRequest(String notes) {
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
        return "RiskComplianceRequest{" +
                "notes='" + notes + '\'' +
                '}';
    }
}
