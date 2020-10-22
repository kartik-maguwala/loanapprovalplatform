package com.socgen.loanapprovalplatform.dto;

import java.io.Serializable;

public class FrontDeskApproveRequest implements Serializable {
    private String notes;

    public FrontDeskApproveRequest() {
    }

    public FrontDeskApproveRequest(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
