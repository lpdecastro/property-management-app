package com.liandrejohn.propertymanagement.property;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PropertyResponse(
        Long id,
        Long workspaceId,
        String name,
        String addressLine,
        String barangay,
        String cityMunicipality,
        String province,
        String postalCode,
        BigDecimal areaSqm,
        String type,
        BigDecimal purchasePrice,
        LocalDate acquisitionDate,
        String titleStatus,
        String registeredOwner,
        boolean titleInOurName,
        BigDecimal amountPaid,
        String notes
) {

    public static PropertyResponse from(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getWorkspace().getId(),
                property.getName(),
                property.getAddressLine(),
                property.getBarangay(),
                property.getCityMunicipality(),
                property.getProvince(),
                property.getPostalCode(),
                property.getAreaSqm(),
                property.getType(),
                property.getPurchasePrice(),
                property.getAcquisitionDate(),
                property.getTitleStatus(),
                property.getRegisteredOwner(),
                property.isTitleInOurName(),
                property.getAmountPaid(),
                property.getNotes()
        );
    }
}