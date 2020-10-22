package com.socgen.loanapprovalplatform.dto;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanType;

public class CarLoanApplicationRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String loantype;
    private Integer amount;
    private String pancardno;
    private String accountno;
    private String ifsccode;
    private String bankname;
    private String address1;
    private String address2;

    public CarLoanApplicationRequest(String firstname, String lastname, String email, String loantype, Integer amount, String pancardno, String accountno, String ifsccode, String bankname, String address1, String address2) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.loantype = loantype;
        this.amount = amount;
        this.pancardno = pancardno;
        this.accountno = accountno;
        this.ifsccode = ifsccode;
        this.bankname = bankname;
        this.address1 = address1;
        this.address2 = address2;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    @Override
    public String toString() {
        return "CarLoanApplicationRequest{" +
                "firstname='" + getFirstname() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", email='" + getEmail() + "'" +
                ", loantype='" + getLoantype() + "'" +
                ", amount=" + getAmount() +
                ", pancardno='" + getPancardno() + "'" +
                ", accountno='" + getAccountno() + "'" +
                ", ifsccode='" + getIfsccode() + "'" +
                ", bankname='" + getBankname() + "'" +
                ", address1='" + getAddress1() + "'" +
                ", address2='" + getAddress2() + "'" +
                "}";
    }

    public CarLoanApplication toModel() {
        return new CarLoanApplication()
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .loantype(LoanType.CAR)
                .amount(this.amount)
                .pancardno(this.pancardno)
                .accountno(this.accountno)
                .ifsccode(this.ifsccode)
                .bankname(this.bankname)
                .address1(this.address1)
                .address2(this.address2);
    }
}
