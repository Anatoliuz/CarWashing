package com.interview.carwash.service;

import com.interview.carwash.dto.OperationPriceCreateDto;
import com.interview.carwash.error.OperationNotFound;
import com.interview.carwash.model.Operation;
import com.interview.carwash.model.OperationPrice;
import com.interview.carwash.repository.OperationPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OperationPriceServiceImpl implements OperationPriceService {

    OperationService service;
    OperationPriceRepository repository;

    @Transactional
    @Override
    public OperationPrice create(OperationPriceCreateDto operationPriceCreateDto) {
        setArchived(operationPriceCreateDto);
        OperationPrice price = new OperationPrice();
        price.setPrice(operationPriceCreateDto.getPrice());
        Operation operation = service.getByName(operationPriceCreateDto.getOperation());
        price.setOperation(operation);
        price.setTime(LocalDateTime.now());
        price.setArchived(false);
        return repository.save(price);
    }

    @Override
    public List<OperationPrice> getByNames(List<String> names) {

        List<OperationPrice> operationPrices = repository.findByNamesIn(names);
        //TODO exc
        if (operationPrices.size() != names.size()) {
            throw new OperationNotFound();
        }
        return operationPrices;
    }

    @Override
    public void setArchived(OperationPriceCreateDto operationPriceCreateDto) {
        List<OperationPrice> ops = repository.findByName(operationPriceCreateDto.getOperation());
        for (OperationPrice op : ops) {
            if (!op.isArchived()) {
                op.setArchived(true);
            }
        }
        repository.saveAll(ops);
    }

    @Override
    public List<OperationPrice> getList(Pageable page) {
        return repository.findAll(page)
                .getContent();
    }

}
