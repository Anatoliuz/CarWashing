package com.interview.carwash.service;

import com.interview.carwash.dto.OperationPriceCreateDto;
import com.interview.carwash.model.OperationPrice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationPriceService {

    OperationPrice create(OperationPriceCreateDto operationPriceCreateDto);

    List<OperationPrice> getByNames(List<String> names);

    void setArchived(OperationPriceCreateDto operationPriceCreateDto);

    List<OperationPrice> getList(Pageable page);

}
