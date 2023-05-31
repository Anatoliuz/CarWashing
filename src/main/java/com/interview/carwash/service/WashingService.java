package com.interview.carwash.service;

import com.interview.carwash.dto.WaitingDto;
import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.model.Washing;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WashingService {

    List<Washing> getList(Pageable page);

    Washing create(WashingCreateDto createDto);

    WaitingDto getWaitingData(Long washingId);

    List<Washing> getQueue(Long washingId);

    List<Washing> getActualQueue();

}
