package com.example.statemachinescheduling.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.statemachinescheduling.service.StateMachineService;

@Service
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private StateMachineService stateMachineService;

    @Scheduled(fixedRate = 3600000)
    public void performTask() {
        stateMachineService.clearOldStates();
    }
}
