package com.example.statemachinescheduling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateMachineConfig.States, StateMachineConfig.Events> {

    public enum States {
        IDLE, RUNNING, FINISHED
    }

    public enum Events {
        START, FINISH
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.IDLE)
                .state(States.RUNNING)
                .end(States.FINISHED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.IDLE).target(States.RUNNING).event(Events.START)
                .and()
                .withExternal()
                .source(States.RUNNING).target(States.FINISHED).event(Events.FINISH);
    }
}
