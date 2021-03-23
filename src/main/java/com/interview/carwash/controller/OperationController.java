package com.interview.carwash.controller;

import com.interview.carwash.dto.OperationCreateDto;
import com.interview.carwash.dto.OperationDto;
import com.interview.carwash.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/operations")
public class OperationController {

    private final OperationService service;
    private final ModelMapper mapper;

    public OperationController(OperationService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<OperationDto> list(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size) {
        return service.getList(PageRequest.of(page, size))
                .stream()
                .map(op -> mapper.map(op, OperationDto.class))
                .collect(toList());
    }

    @PostMapping
    public OperationDto post(@RequestBody @Valid
                                     OperationCreateDto body) {
        var operation = service.create(body);
        return mapper.map(
                operation,
                OperationDto.class
        );
    }

}
