package com.interview.carwash.repository;

import com.interview.carwash.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByNameIn(List<String> names);

}
