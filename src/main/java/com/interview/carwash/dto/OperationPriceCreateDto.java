package com.interview.carwash.dto;

import com.interview.carwash.validation.OperationConstraint;
import lombok.Data;

@Data
public class OperationPriceCreateDto {

    @OperationConstraint
    private String operation;
    private int price;

}
