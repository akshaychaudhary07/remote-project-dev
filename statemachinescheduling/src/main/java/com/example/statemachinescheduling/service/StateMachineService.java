package com.example.statemachinescheduling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.statemachinescheduling.config.StateMachineConfig.States;
import com.example.statemachinescheduling.config.StateMachineConfig.Events;
import com.example.statemachinescheduling.model.StateEntity;
import com.example.statemachinescheduling.repository.StateRepository;

import java.time.LocalDateTime;

@Service
public class StateMachineService {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    private StateRepository stateRepository;

    public void startProcess() {
        stateMachine.start();
        stateMachine.sendEvent(Events.START);
    }

    public void finishProcess() {
        stateMachine.sendEvent(Events.FINISH);
    }

    public StateEntity createState(String state) {
        StateEntity newState = new StateEntity();
        newState.setState(state);
        newState.setCreatedAt(LocalDateTime.now());
        newState.setDeletedAt(null);
        return stateRepository.save(newState);
    }


    @Transactional
    public void clearOldStates() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(1);
        System.out.println("Deleting states older than: " + cutoff);
        stateRepository.deleteByCreatedAtBefore(cutoff);
    }
}
