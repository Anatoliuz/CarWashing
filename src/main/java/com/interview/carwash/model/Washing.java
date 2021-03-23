package com.interview.carwash.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
            name = "washing_operation",
            joinColumns = @JoinColumn(name = "washing_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private List<Operation> operations;

}
