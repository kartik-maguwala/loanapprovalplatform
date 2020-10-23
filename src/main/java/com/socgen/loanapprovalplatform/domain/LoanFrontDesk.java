package com.socgen.loanapprovalplatform.domain;

import com.socgen.loanapprovalplatform.domain.enumeration.LoanFrontDeskStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_front_desk")
public class LoanFrontDesk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanFrontDeskStatus status;

    @Column(name = "notes")
    private String notes = "";


    @Column(name = "createdon", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdon;

    @Column(name = "modifiedon", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedon;

    @OneToOne
    @JoinColumn(name = "carLoanApplicationId")
    private CarLoanApplication carLoanApplication;


    public LoanFrontDesk carLoanApplication(CarLoanApplication carLoanApplication) {
        this.carLoanApplication = carLoanApplication;
        return this;
    }

    public CarLoanApplication getCarLoanApplication() {
        return carLoanApplication;
    }

    public void setCarLoanApplication(CarLoanApplication carLoanApplication) {
        this.carLoanApplication = carLoanApplication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanFrontDeskStatus getStatus() {
        return status;
    }

    public LoanFrontDesk status(LoanFrontDeskStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(LoanFrontDeskStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public LoanFrontDesk notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public LoanFrontDesk createdon(LocalDateTime createdon) {
        this.createdon = createdon;
        return this;
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public LocalDateTime getModifiedon() {
        return modifiedon;
    }

    public LoanFrontDesk modifiedon(LocalDateTime modifiedon) {
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
        if (!(o instanceof LoanFrontDesk)) {
            return false;
        }
        return id != null && id.equals(((LoanFrontDesk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LoanFrontDesk{" +
                "id=" + getId() +
                ", status='" + getStatus() + "'" +
                ", notes='" + getNotes() + "'" +
                ", createdon='" + getCreatedon() + "'" +
                ", modifiedon='" + getModifiedon() + "'" +
                "}";
    }

    public Long getCarLoanApplicationId() {
        return this.carLoanApplication.getId();
    }
}