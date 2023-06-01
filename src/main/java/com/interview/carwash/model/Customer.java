package com.interview.carwash.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Customer {

    private String name;
    private String sex;

    @JsonCreator
    public Customer(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty(value = "sex", required = true) String sex) {
        this.name = name;
        this.sex = sex;
    }

}
