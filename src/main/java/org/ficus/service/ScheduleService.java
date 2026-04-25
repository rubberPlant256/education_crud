package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Schedule;
import org.ficus.data.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
