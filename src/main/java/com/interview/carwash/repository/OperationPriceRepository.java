package com.interview.carwash.repository;

import com.interview.carwash.model.OperationPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationPriceRepository extends JpaRepository<OperationPrice, Long> {

    @Query(
            value = "select op from OperationPrice op where op.operation.name = :name"
    )
    List<OperationPrice> findByName(@Param("name") String name);

    @Query(
            value = "select op from OperationPrice op where op.operation.name in :names and op.isArchived = false"
    )
    List<OperationPrice> findByNamesIn(@Param("names") List<String> names);

//    void setDatabase(String database);

}
