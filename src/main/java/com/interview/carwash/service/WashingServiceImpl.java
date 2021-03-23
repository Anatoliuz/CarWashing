package com.interview.carwash.service;

import com.interview.carwash.dto.WaitingDto;
import com.interview.carwash.dto.WashingCreateDto;
import com.interview.carwash.error.WashingNotFound;
import com.interview.carwash.model.Operation;
import com.interview.carwash.model.Washing;
import com.interview.carwash.repository.WashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WashingServiceImpl implements WashingService {

    private final WashingRepository repository;
    private final OperationService service;

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
        List<Operation> operations = service.getAllByNames(names);
        washing.setOperations(operations);
        int minutes = getWashingTime(operations);
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
        return repository.save(washing);
    }

    @Override
    public WaitingDto getWaitingData(Long washingId) {
        LocalDateTime now = LocalDateTime.now();

        var washing = repository.findById(washingId)
                .orElseThrow(WashingNotFound::new);
        LocalDateTime startOfRequestedWashing = washing.getStartTime();
        List<Washing> queue = repository.findWashingQueue(now, startOfRequestedWashing);
        long minutes = ChronoUnit.MINUTES.between(now, startOfRequestedWashing);
        return new WaitingDto(minutes, queue.size() + 1);
    }


    private int getWashingTime(List<Operation> operations) {
        int minutes = 0;
        for (Operation op : operations) {
            minutes += op.getMinutes();
        }
        return minutes;
    }

}
