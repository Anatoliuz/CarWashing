package com.interview.carwash.dto;

import lombok.Data;

@Data
public class WashingDto extends WashingCreateDto {

    private Long id;
    private int price;

}
