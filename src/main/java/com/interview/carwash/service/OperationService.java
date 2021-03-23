package com.interview.carwash.service;

import com.interview.carwash.dto.OperationCreateDto;
import com.interview.carwash.model.Operation;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationService {

    List<Operation> getList(Pageable page);

    Operation create(OperationCreateDto operationCreateDto);

    List<Operation> getAllByNames(List<String> names);

}
