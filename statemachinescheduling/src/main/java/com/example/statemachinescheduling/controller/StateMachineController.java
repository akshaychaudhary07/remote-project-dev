package com.example.statemachinescheduling.controller;

import com.example.statemachinescheduling.model.StateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.statemachinescheduling.service.StateMachineService;

@RestController
@RequestMapping("/api/statemachine")
public class StateMachineController {

    @Autowired
    private StateMachineService stateMachineService;

    @GetMapping("/start")
    public ResponseEntity<String> startMachine() {
        stateMachineService.startProcess();
        return ResponseEntity.ok("State machine started and transitioned to RUNNING");
    }

    @GetMapping("/finish")
    public ResponseEntity<String> finishMachine() {
        stateMachineService.finishProcess();
        return ResponseEntity.ok("State machine transitioned to FINISHED");
    }

    // Adding a new method to create a state
    @PostMapping("/create")
    public ResponseEntity<StateEntity> createState(@RequestBody String state) {
        StateEntity createdState = stateMachineService.createState(state);
        return ResponseEntity.ok(createdState);
    }
}