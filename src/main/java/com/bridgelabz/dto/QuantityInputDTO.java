package com.bridgelabz.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityInputDTO {

    @Valid
    private QuantityDTO firstQuantity;

    @Valid
    private QuantityDTO secondQuantity;

    private String targetUnit;
}