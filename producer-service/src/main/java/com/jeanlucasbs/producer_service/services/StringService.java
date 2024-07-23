package com.jeanlucasbs.producer_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class StringService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String exchangeMarketPlace;

    private String routingKeyProductLog;

    public StringService(RabbitTemplate rabbitTemplate,
                         @Value("${rabbitmq.marketplace.exchange}") String exchangeMarketPlace,
                         @Value("${rabbitmq.product.routingkey}") String routingKeyProductLog) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeMarketPlace = exchangeMarketPlace;
        this.routingKeyProductLog = routingKeyProductLog;
    }

    public void produce(String message){
        log.info("Received message " + message);
        rabbitTemplate.convertAndSend(exchangeMarketPlace, routingKeyProductLog, message);
    }
}
