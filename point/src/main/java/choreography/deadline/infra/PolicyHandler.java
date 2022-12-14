package choreography.deadline.infra;

import choreography.deadline.config.kafka.KafkaProcessor;
import choreography.deadline.domain.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ExchangeSucceed'"
    )
    public void wheneverExchangeSucceed_UsePoint(
        @Payload ExchangeSucceed exchangeSucceed
    ) {
        ExchangeSucceed event = exchangeSucceed;
        System.out.println(
            "\n\n##### listener UsePoint : " + exchangeSucceed + "\n\n"
        );
        Point.usePoint(event);
    }

    //QUIZ1: comment here for quiz 1
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderRejected'"
    )
    public void wheneverOrderRejected_Compensate(
        @Payload OrderRejected orderRejected
    ) {
        OrderRejected event = orderRejected;
        System.out.println(
            "\n\n##### listener Compensate : " + orderRejected + "\n\n"
        );

        // Sample Logic //
        Point.compensate(event);
    }


    //QUIZ1: uncomment here for quiz 1
    // @StreamListener(
    //     value = KafkaProcessor.INPUT,
    //     condition = "headers['type']=='ExchangeCompensated'"
    // )
    // public void wheneverExchangeCompensated_Compensate(
    //     @Payload ExchangeCompensated exchangeCompensated
    // ) {
    //     Point.compensate(exchangeCompensated);
    // }

}
