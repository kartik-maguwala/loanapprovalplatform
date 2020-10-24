package com.socgen.loanapprovalplatform.domain;

import com.socgen.loanapprovalplatform.domain.enumeration.CarLoanDisburseStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "car_loan_disburse_info")
public class CarLoanDisburseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "disbursed_amount", nullable = false)
    private Integer disbursedAmount;

    @Column(name = "transactionid")
    private String transactionid;

    @Column(name = "branchifsc")
    private String branchifsc;

    @Column(name = "disburmentdate")
    private Date disbursedOn;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarLoanDisburseStatus status;

    @Column(name = "createdon")
    @CreationTimestamp
    private Instant createdon;

    @Column(name = "modifiedon")
    @UpdateTimestamp
    private Instant modifiedon;

    @OneToOne
    @JoinColumn(name = "carLoanApplicationId")
    private CarLoanApplication carLoanApplication;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDisbursedAmount() {
        return disbursedAmount;
    }

    public CarLoanDisburseInfo disbursedAmount(Integer disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
        return this;
    }

    public void setDisbursedAmount(Integer disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public CarLoanDisburseInfo transactionid(String transactionid) {
        this.transactionid = transactionid;
        return this;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getBranchifsc() {
        return branchifsc;
    }

    public CarLoanDisburseInfo branchifsc(String branchifsc) {
        this.branchifsc = branchifsc;
        return this;
    }

    public void setBranchifsc(String branchifsc) {
        this.branchifsc = branchifsc;
    }

    public Date getDisbursedOn() {
        return disbursedOn;
    }

    public CarLoanDisburseInfo disbursedOn(Date disburmentdate) {
        this.disbursedOn = disburmentdate;
        return this;
    }

    public void setDisbursedOn(Date disbursedOn) {
        this.disbursedOn = disbursedOn;
    }

    public CarLoanDisburseStatus getStatus() {
        return status;
    }

    public CarLoanDisburseInfo status(CarLoanDisburseStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CarLoanDisburseStatus status) {
        this.status = status;
    }

    public Instant getCreatedon() {
        return createdon;
    }

    public CarLoanDisburseInfo createdon(Instant createdon) {
        this.createdon = createdon;
        return this;
    }

    public void setCreatedon(Instant createdon) {
        this.createdon = createdon;
    }

    public Instant getModifiedon() {
        return modifiedon;
    }

    public CarLoanDisburseInfo modifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(Instant modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Long getCarLoanApplicationId() {
        return this.carLoanApplication.getId();
    }

    public CarLoanDisburseInfo carLoanApplication(CarLoanApplication carLoanApplication) {
        this.carLoanApplication = carLoanApplication;
        return this;
    }

    public CarLoanApplication getCarLoanApplication() {
        return carLoanApplication;
    }

    public void setCarLoanApplication(CarLoanApplication carLoanApplication) {
        this.carLoanApplication = carLoanApplication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarLoanDisburseInfo)) {
            return false;
        }
        return id != null && id.equals(((CarLoanDisburseInfo) o).id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "CarLoanDisburseInfo{" +
                "id=" + getId() +
                ", disbursedAmount=" + getDisbursedAmount() +
                ", transactionid='" + getTransactionid() + "'" +
                ", branchifsc='" + getBranchifsc() + "'" +
                ", disbursedOn='" + getDisbursedOn() + "'" +
                ", status='" + getStatus() + "'" +
                ", createdon='" + getCreatedon() + "'" +
                ", modifiedon='" + getModifiedon() + "'" +
                "}";
    }
}
