package com.interview.carwash.dto;

import com.interview.carwash.validation.OperationConstraint;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Data
public class WashingCreateDto {

    @NotEmpty
    @NotNull
    private List<@OperationConstraint String> operations;

}
