package com.interview.carwash.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Validated
public class OperationCreateDto {

    @NotEmpty
    private String name;

    @Positive
    private int minutes;

}
