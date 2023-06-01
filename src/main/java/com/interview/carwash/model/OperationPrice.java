package com.interview.carwash.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "carwash", name = "operation_price")
@Data
public class OperationPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    private int price;

    @Column(name = "create_date_time")
    private LocalDateTime time;

    @Column(name = "is_archived")
    private boolean isArchived;

}
