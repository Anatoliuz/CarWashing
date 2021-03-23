package com.interview.carwash.controller;

import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.dto.WashingCreateFormDto;
import com.interview.carwash.service.OperationService;
import com.interview.carwash.service.WashingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/carwashing")
@AllArgsConstructor
public class UiController {

    private final OperationService operationService;
    private final WashingService washingService;
    private final ModelMapper mapper;

    @GetMapping
    public ModelAndView washing() {
        Map<String, Object> map = new HashMap<>();
        map.put("operations", operationService.getList(PageRequest.of(0, 20)));
        map.put("washings", washingService.getList(PageRequest.of(0, 20)));
        return new ModelAndView("washing", map);
    }

    @PostMapping
    public ModelAndView post(WashingCreateDto dto) {
        Map<String, Object> map = new HashMap<>();
       // var washing = washingService.create();

        map.put("operations", operationService.getList(PageRequest.of(0, 20)));
        map.put("washings", washingService.getList(PageRequest.of(0, 20)));

        return new ModelAndView("washing", map);
    }

}
