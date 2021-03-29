package com.interview.carwash.controller;

import com.interview.carwash.dto.WaitingDto;
import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.dto.WashingDto;
import com.interview.carwash.service.WashingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/washing")
@AllArgsConstructor
public class WashingController {

    private final WashingService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<WashingDto> getList(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "20") int size) {
        return service.getList(PageRequest.of(page, size))
                .stream()
                .map(washing -> mapper.map(washing, WashingDto.class))
                .collect(toList());
    }

    @PostMapping
    public WashingDto post(@RequestBody @Valid
                                   WashingCreateDto createDto) {
        var washing = service.create(createDto);
        return mapper.map(
                washing,
                WashingDto.class
        );
    }

    @GetMapping("/wait/{id}")
    public WaitingDto getWaitingData(@PathVariable Long id) {
        return service.getWaitingData(id);
    }

}
