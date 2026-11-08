package com.liandrejohn.propertymanagement.property;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PropertyRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 255)
        String addressLine,

        @Size(max = 100)
        String barangay,

        @NotBlank
        @Size(max = 100)
        String cityMunicipality,

        @NotBlank
        @Size(max = 100)
        String province,

        @Size(max = 10)
        String postalCode,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = false)
        @Digits(integer = 13, fraction = 2)
        BigDecimal areaSqm,

        @NotBlank
        @Size(max = 50)
        String type,

        @NotNull
        @DecimalMin("0.00")
        @Digits(integer = 17, fraction = 2)
        BigDecimal purchasePrice,

        LocalDate acquisitionDate,

        @Size(max = 50)
        String titleStatus,

        @Size(max = 150)
        String registeredOwner,

        @NotNull
        Boolean titleInOurName,

        @NotNull
        @DecimalMin("0.00")
        @Digits(integer = 17, fraction = 2)
        BigDecimal amountPaid,

        @Size(max = 5000)
        String notes

) {
}