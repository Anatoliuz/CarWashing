package com.interview.carwash.service;

import com.interview.carwash.aspect.Cacheable;
import com.interview.carwash.aspect.ExecuteOnMyPool;
import com.interview.carwash.dto.OperationPriceCreateDto;
import com.interview.carwash.error.OperationNotFound;
import com.interview.carwash.model.Operation;
import com.interview.carwash.model.OperationPrice;
import com.interview.carwash.repository.OperationPriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OperationPriceServiceImpl implements OperationPriceService {

    private OperationService service;
    private OperationPriceRepository repository;
    private ObjectProvider<OperationPriceService> self;

    @Transactional
    @Override
    public OperationPrice create(OperationPriceCreateDto operationPriceCreateDto) {
//        log.info(Thread.currentThread().getName());

        self.getObject().setArchived(operationPriceCreateDto);

//        log.info("AFTER ARCHIVED: " + Thread.currentThread().getName());

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

    @ExecuteOnMyPool
//    @Transactional
    @Override
    public void setArchived(OperationPriceCreateDto operationPriceCreateDto) {
        List<OperationPrice> ops = repository.findByName(operationPriceCreateDto.getOperation());
        for (OperationPrice op : ops) {
            if (!op.isArchived()) {
                op.setArchived(true);
            }
        }
        repository.saveAll(ops);
//        log.info("REAL ARCHIVED: " + Thread.currentThread().getName());
    }

    @Cacheable
    @Override
    public List<OperationPrice> getList(Pageable page) {
        return repository.findAll(page)
                .getContent();
    }

}
