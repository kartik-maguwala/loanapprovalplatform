package com.socgen.loanapprovalplatform.dto;

public class CarLoanApplicationDetailedResponse {
    private Long applicationId;
    private String firstName;
    private String lasNname;
    private String email;
    private String loantype;
    private Integer amount;
    private String pancardno;
    private String accountno;
    private String ifsccode;
    private String bankname;
    private String address1;
    private String address2;
    private String status;

    public CarLoanApplicationDetailedResponse(Long applicationId, String firstName, String lasNname, String email,
                                              String loantype, Integer amount, String pancardno, String accountno,
                                              String ifsccode, String bankname, String address1, String address2,
                                              String status) {
        this.applicationId = applicationId;
        this.firstName = firstName;
        this.lasNname = lasNname;
        this.email = email;
        this.loantype = loantype;
        this.amount = amount;
        this.pancardno = pancardno;
        this.accountno = accountno;
        this.ifsccode = ifsccode;
        this.bankname = bankname;
        this.address1 = address1;
        this.address2 = address2;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLasNname() {
        return lasNname;
    }

    public String getEmail() {
        return email;
    }

    public String getLoantype() {
        return loantype;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPancardno() {
        return pancardno;
    }

    public String getAccountno() {
        return accountno;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public String getBankname() {
        return bankname;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getStatus() {
        return status;
    }
}
