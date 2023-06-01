package com.interview.carwash.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "carwash", name = "washing")
@Data
public class Washing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @Column(name = "delete_date_time")
    private LocalDateTime deleteDateTime;

    @ManyToMany
    @JoinTable(
            schema = "carwash",
            name = "washing_operation_price",
            joinColumns = @JoinColumn(name = "washing_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_price_id")
    )
    private List<OperationPrice> operationsPrices;

}
