package com.socgen.loanapprovalplatform.domain;

import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanStatus;
import com.socgen.loanapprovalplatform.domain.enumeration.LoanType;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationDetailedResponse;
import com.socgen.loanapprovalplatform.dto.CarLoanApplicationResponse;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_loan_application")
public class CarLoanApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;


    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "loantype", nullable = false)
    private LoanType loantype;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @Column(name = "pancardno", nullable = false)
    private String pancardno;

    @NotNull
    @Column(name = "accountno", nullable = false)
    private String accountno;

    @NotNull
    @Column(name = "ifsccode", nullable = false)
    private String ifsccode;

    @NotNull
    @Column(name = "bankname", nullable = false)
    private String bankname;

    @NotNull
    @Column(name = "address_1", nullable = false)
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarLoanStatus status = CarLoanStatus.PENDING;

    @Column(name = "createdon")
    @CreationTimestamp
    private LocalDateTime createdon;

    @Column(name = "modifiedon")
    @UpdateTimestamp
    private LocalDateTime modifiedon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public CarLoanApplication firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public CarLoanApplication lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public CarLoanApplication email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoanType getLoantype() {
        return loantype;
    }

    public CarLoanApplication loantype(LoanType loantype) {
        this.loantype = loantype;
        return this;
    }

    public void setLoantype(LoanType loantype) {
        this.loantype = loantype;
    }

    public Integer getAmount() {
        return amount;
    }

    public CarLoanApplication amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPancardno() {
        return pancardno;
    }

    public CarLoanApplication pancardno(String pancardno) {
        this.pancardno = pancardno;
        return this;
    }

    public void setPancardno(String pancardno) {
        this.pancardno = pancardno;
    }

    public String getAccountno() {
        return accountno;
    }

    public CarLoanApplication accountno(String accountno) {
        this.accountno = accountno;
        return this;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public CarLoanApplication ifsccode(String ifsccode) {
        this.ifsccode = ifsccode;
        return this;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getBankname() {
        return bankname;
    }

    public CarLoanApplication bankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAddress1() {
        return address1;
    }

    public CarLoanApplication address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public CarLoanApplication address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public CarLoanStatus getStatus() {
        return status;
    }

    public CarLoanApplication status(CarLoanStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CarLoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public CarLoanApplication createdon(LocalDateTime createdon) {
        this.createdon = createdon;
        return this;
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public LocalDateTime getModifiedon() {
        return modifiedon;
    }

    public CarLoanApplication modifiedon(LocalDateTime modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(LocalDateTime modifiedon) {
        this.modifiedon = modifiedon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarLoanApplication)) {
            return false;
        }
        return id != null && id.equals(((CarLoanApplication) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CarLoanApplication{" +
                "id=" + getId() +
                ", firstname='" + getFirstname() + "'" +
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
                ", status='" + getStatus() + "'" +
                ", createdon='" + getCreatedon() + "'" +
                ", modifiedon='" + getModifiedon() + "'" +
                "}";
    }

    public CarLoanApplicationResponse toResponseDto() {
        return new CarLoanApplicationResponse(this.id);
    }

    public CarLoanApplicationDetailedResponse toDetailedResponseDto() {
        return new CarLoanApplicationDetailedResponse(this.id, this.firstname, this.lastname, this.email, this.loantype.name(),
                this.amount, this.pancardno, this.accountno, this.ifsccode, this.bankname, this.address1, this.address2, this.status.name());
    }
}



