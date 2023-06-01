package com.interview.carwash.service;

import com.interview.carwash.aspect.CustomerSerializable;
import com.interview.carwash.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyServiceImpl implements MyService {

    @CustomerSerializable
    @Override
    public void serial(Object o) {
        if (Customer.class.isAssignableFrom(o.getClass())) {
            log.info("Customer name: " + ((Customer) o).getName());
            return;
        }
        throw new RuntimeException("Unsupported type exception");
    }

}
