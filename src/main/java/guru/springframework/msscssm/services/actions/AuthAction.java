package guru.springframework.msscssm.services.actions;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
@RequiredArgsConstructor
public class AuthAction implements Action<PaymentState, PaymentEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AuthAction.class);

    private final String paymentIdHeader;

    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        logger.info("Auth was called!!!");

        if (new Random().nextInt(10) < 8) {
            logger.info("Auth Approved");
            context.getStateMachine().sendEvent(
                MessageBuilder.withPayload(PaymentEvent.AUTH_APPROVED)
                        .setHeader(paymentIdHeader, context.getMessageHeader(paymentIdHeader))
                        .build()
            );
        } else {
            logger.warn("Auth Declined! No Credit!!!!!!");
            context.getStateMachine().sendEvent(
                MessageBuilder.withPayload(PaymentEvent.AUTH_DECLINED)
                        .setHeader(paymentIdHeader, context.getMessageHeader(paymentIdHeader))
                        .build()
            );
        }
    }
}
