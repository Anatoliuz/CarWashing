package com.interview.carwash.service;

import com.interview.carwash.dto.OperationCreateDto;
import com.interview.carwash.error.InvalidOperationName;
import com.interview.carwash.model.Operation;
import com.interview.carwash.repository.OperationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository repository;

    public OperationServiceImpl(OperationRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operation> getList(Pageable page) {
        return repository.findAll(page)
                .getContent();
    }

    @Transactional
    @Override
    public Operation create(OperationCreateDto createDto) {
        Operation operation = new Operation();
        operation.setName(createDto.getName());
        operation.setMinutes(createDto.getMinutes());
        return repository.save(operation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operation> getAllByNames(List<String> names) {
        List<Operation> found = repository.findAllByNameIn(names);
        if (names.size() != found.size()) {
            throw new InvalidOperationName();
        }
        return found;
    }


}
