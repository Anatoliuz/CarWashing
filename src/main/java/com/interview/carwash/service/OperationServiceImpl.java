package com.interview.carwash.service;

import com.interview.carwash.dto.OperationCreateDto;
import com.interview.carwash.error.OperationNotFound;
import com.interview.carwash.model.Operation;
import com.interview.carwash.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository repository;

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
        operation.setLabel(createDto.getLabel());
        operation.setMinutes(createDto.getMinutes());
        return repository.save(operation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operation> getAllByNames(List<String> names) {
        List<Operation> found = repository.findAllByNameIn(names);
        if (names.size() != found.size()) {
            throw new OperationNotFound();
        }
        return found;
    }

    @Transactional
    @Override
    public Operation getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(OperationNotFound::new);
    }


}
