package com.socgen.loanapprovalplatform.dto;

import com.socgen.loanapprovalplatform.domain.CarLoanApplication;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarLoanApplicationRequest {

    @NotBlank(message = "Please enter firstname")
    @Size(max=50, message="Name should have maximum 50 characters")
    private String firstname;


    @NotBlank(message = "Please enter lastname")
    @Size(max=50, message="Name should have maximum 50 characters")
    private String lastname;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 1)
    private Integer amount;

    @NotBlank(message = "Please enter pancard")
    @Size(max=20, message="PanCard should have maximum 20 characters")
    private String pancardno;

    @NotBlank(message = "Please enter account number")
    @Size(max=20, message="Account Number should have maximum 20 characters")
    private String accountno;

    @NotBlank(message = "Please enter IFSC code")
    @Size(max=20, message="Name should have maximum 20 characters")
    private String ifsccode;

    @NotBlank(message = "Please enter bank name")
    @Size(max=50, message="Name should have maximum 50 characters")
    private String bankname;

    @NotBlank(message = "Please enter address1")
    @Size(max=255, message="Name should have maximum 255 characters")
    private String address1;

    @Size(max=255, message="Name should have maximum 255 characters")
    private String address2;

    public CarLoanApplicationRequest() {
    }

    public CarLoanApplicationRequest(String firstname, String lastname, String email, Integer amount, String pancardno, String accountno, String ifsccode, String bankname, String address1, String address2) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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
