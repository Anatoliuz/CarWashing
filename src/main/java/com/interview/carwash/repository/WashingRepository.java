package com.interview.carwash.repository;

import com.interview.carwash.model.Washing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WashingRepository extends JpaRepository<Washing, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Washing> findFirstByOrderByEndTimeDesc();

    @Query(
            value = "select w from Washing w where w.endTime > :startTime " +
                    "and w.endTime <= :endTime"
    )
    List<Washing> findWashingQueue(
            @Param("startTime")LocalDateTime start,
            @Param("endTime") LocalDateTime endTime
    );

}
