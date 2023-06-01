package com.interview.carwash.controller;

import com.interview.carwash.aspect.CustomerSerializable;
import com.interview.carwash.aspect.LoggableTimeExceeding;
import com.interview.carwash.dto.WaitingDto;
import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.service.MyService;
import com.interview.carwash.service.OperationPriceService;
import com.interview.carwash.service.WashingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UiController {

    private final OperationPriceService operationPriceService;
    private final WashingService washingService;
    private final MyService myService;

    @LoggableTimeExceeding
    @GetMapping("/carwashing")
    public ModelAndView washing() {
        Map<String, Object> map = new HashMap<>();
        map.put("operations", operationPriceService.getList(PageRequest.of(0, 20)));
        map.put("washings", washingService.getActualQueue());
        map.put("formatter", DateTimeFormatter.ofPattern("dd/MM/uuuu hh:mm"));
        return new ModelAndView("washing", map);
    }

    @CustomerSerializable
    @PostMapping("/serial")
    public ResponseEntity<String> serial(@RequestBody Map<String, Object> body) {
        myService.serial(body);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @PostMapping("/carwashing")
    public ModelAndView post(WashingCreateDto dto) {
        Map<String, Object> map = new HashMap<>();
        var washing = washingService.create(dto);
        WaitingDto waitingData = washingService.getWaitingData(washing.getId());
        map.put("number", washing.getId());
        map.put("price", waitingData.getPrice());

        return new ModelAndView("number", map);
    }

    @GetMapping("/number")
    public ModelAndView getNumber(@RequestParam Long washingId) {
        Map<String, Object> map = new HashMap<>();
        WaitingDto waitingData = washingService.getWaitingData(washingId);
        map.put("number", washingId);
        map.put("price", waitingData.getPrice());
        return new ModelAndView("number", map);
    }

    @GetMapping("/queue")
    public ModelAndView queue(@RequestParam(required = false) Long washingId) {
        Map<String, Object> map = new HashMap<>();
        if (washingId != null) {
            WaitingDto waitingData = washingService.getWaitingData(washingId);
            map.put("washing", waitingData);
            map.put("queue", washingService.getQueue(washingId));
            map.put("formatter", DateTimeFormatter.ofPattern("dd/MM/uuuu hh:mm"));
        }
        map.put("washingId", washingId);
        return new ModelAndView("queue", map);
    }

}
