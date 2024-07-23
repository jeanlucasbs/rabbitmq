package com.jeanlucasbs.consumer_service.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StringConsumerListener {

    @RabbitListener(queues = "${rabbitmq.product.queue}")
    public void consumer(String message){
        log.info("Consumer received a message " + message);

    }
}
