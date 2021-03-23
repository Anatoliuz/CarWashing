package com.interview.carwash.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Data
public class WashingCreateFormDto {

    @NotEmpty
    @NotNull
    private String operations;

}
