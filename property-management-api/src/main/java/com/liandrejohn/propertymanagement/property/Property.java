package com.liandrejohn.propertymanagement.property;

import com.liandrejohn.propertymanagement.workspace.Workspace;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "properties")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 255)
    private String addressLine;

    @Column(length = 100)
    private String barangay;

    @Column(nullable = false, length = 100)
    private String cityMunicipality;

    @Column(nullable = false, length = 100)
    private String province;

    @Column(length = 10)
    private String postalCode;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal areaSqm;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal purchasePrice;

    private LocalDate acquisitionDate;

    @Column(length = 50)
    private String titleStatus;

    @Column(length = 150)
    private String registeredOwner;

    @Column(nullable = false)
    private boolean titleInOurName;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amountPaid;

    @Column(length = 5000)
    private String notes;

    public Property(Workspace workspace, PropertyRequest request) {
        this.workspace = workspace;
        update(request);
    }

    public void update(PropertyRequest request) {
        this.name = request.name().strip();
        this.addressLine = request.addressLine();
        this.barangay = request.barangay();
        this.cityMunicipality = request.cityMunicipality().strip();
        this.province = request.province().strip();
        this.postalCode = request.postalCode();
        this.areaSqm = request.areaSqm();
        this.type = request.type().strip();
        this.purchasePrice = request.purchasePrice();
        this.acquisitionDate = request.acquisitionDate();
        this.titleStatus = request.titleStatus();
        this.registeredOwner = request.registeredOwner();
        this.titleInOurName = request.titleInOurName();
        this.amountPaid = request.amountPaid();
        this.notes = request.notes();
    }
}