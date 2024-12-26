package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
@Slf4j
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());

        sm.start();

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.AUTHORIZE);

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.AUTH_APPROVED);

        log.info("Current state: {}", sm.getState().toString());

        sm.sendEvent(PaymentEvent.AUTH_DECLINED);

        log.info("Current state: {}", sm.getState().toString());


    }
}