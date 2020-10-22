package com.socgen.loanapprovalplatform.domain;

import com.socgen.loanapprovalplatform.domain.enumeration.RiskComplianceStatus;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_compliance")
public class RiskCompliance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RiskComplianceStatus status;

    @Column(name = "notes")
    private String notes;

    @NotNull
    @Column(name = "createdon", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdon;

    @NotNull
    @Column(name = "modifiedon", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedon;

    @NotNull
    @Column(name = "carLoanApplicationId", nullable = false)
    private Long carLoanApplicationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RiskComplianceStatus getStatus() {
        return status;
    }

    public RiskCompliance status(RiskComplianceStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RiskComplianceStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public RiskCompliance notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public RiskCompliance createdon(LocalDateTime createdon) {
        this.createdon = createdon;
        return this;
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public LocalDateTime getModifiedon() {
        return modifiedon;
    }

    public RiskCompliance modifiedon(LocalDateTime modifiedon) {
        this.modifiedon = modifiedon;
        return this;
    }

    public void setModifiedon(LocalDateTime modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Long getCarLoanApplicationId() {
        return carLoanApplicationId;
    }

    public RiskCompliance carLoanApplicationId(Long carLoanApplicationId) {
        this.carLoanApplicationId = carLoanApplicationId;
        return this;
    }

    public void setCarLoanApplication(Long carLoanApplicationId) {
        this.carLoanApplicationId = carLoanApplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskCompliance)) {
            return false;
        }
        return id != null && id.equals(((RiskCompliance) o).id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "RiskCompliance{" +
                "id=" + getId() +
                ", status='" + getStatus() + "'" +
                ", notes='" + getNotes() + "'" +
                ", createdon='" + getCreatedon() + "'" +
                ", modifiedon='" + getModifiedon() + "'" +
                "}";
    }
}
