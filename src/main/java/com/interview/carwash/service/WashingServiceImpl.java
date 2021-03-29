package com.interview.carwash.service;

import com.interview.carwash.dto.WaitingDto;
import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.error.WashingNotFound;
import com.interview.carwash.model.Operation;
import com.interview.carwash.model.OperationPrice;
import com.interview.carwash.model.Washing;
import com.interview.carwash.repository.WashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WashingServiceImpl implements WashingService {

    private final WashingRepository repository;
    private final OperationService service;
    private final OperationPriceService operationPriceService;

    @Transactional(readOnly = true)
    @Override
    public List<Washing> getList(Pageable page) {
        return repository.findAll(page)
                .getContent();
    }

    @Transactional
    @Override
    public Washing create(WashingCreateDto createDto) {
        Washing washing = new Washing();
        List<String> names = createDto.getOperations();

        List<OperationPrice> operationPrices = operationPriceService.getByNames(names);
        //TODO

        int minutes = getWashingTime(operationPrices
                .stream()
                .map(OperationPrice::getOperation)
                .collect(Collectors.toList()));
        LocalDateTime now = LocalDateTime.now();

        var nextWashStartTime = repository.findFirstByOrderByEndTimeDesc()
                .map(Washing::getEndTime)
                .filter(time -> time.isAfter(now))
                .orElse(now);

        washing.setStartTime(nextWashStartTime);
        washing.setEndTime(
                nextWashStartTime.plusMinutes(minutes)
        );
        washing.setCreateDateTime(now);
        washing.setOperationsPrices(operationPrices);
        return repository.save(washing);
    }

    @Override
    public WaitingDto getWaitingData(Long washingId) {
        LocalDateTime now = LocalDateTime.now();

        var washing = repository.findById(washingId)
                .orElseThrow(WashingNotFound::new);
        int price = washing.getOperationsPrices()
                .stream()
                .map(OperationPrice::getPrice)
                .reduce(0, Integer::sum);

        LocalDateTime startOfRequestedWashing = washing.getStartTime();
        List<Washing> queue = repository.findWashingQueue(now, startOfRequestedWashing);
        long minutes = ChronoUnit.MINUTES.between(now, startOfRequestedWashing);
        return new WaitingDto(minutes, queue.size() + 1, price);
    }


    @Override
    public List<Washing> getQueue(Long washingId) {
        LocalDateTime now = LocalDateTime.now();
        var washing = repository.findById(washingId)
                .orElseThrow(WashingNotFound::new);
        LocalDateTime startOfRequestedWashing = washing.getStartTime();
        return repository.findWashingQueue(now, startOfRequestedWashing);
    }

    @Override
    public List<Washing> getActualQueue() {
        return repository.findActualQueue(LocalDateTime.now());
    }

    private int getWashingTime(List<Operation> operations) {
        int minutes = 0;
        for (Operation op : operations) {
            minutes += op.getMinutes();
        }
        return minutes;
    }

}
