package com.interview.carwash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingDto {

    private long minutesToWait;

    private int numInQueue;

    private int price;

}
