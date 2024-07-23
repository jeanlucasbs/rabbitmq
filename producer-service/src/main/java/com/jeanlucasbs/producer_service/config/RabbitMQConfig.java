package com.jeanlucasbs.producer_service.config;

import com.rabbitmq.client.AMQP;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EventListener;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.marketplace.exchange}")
    private String exchangeMarketPlace;

    @Value("${rabbitmq.product.queue}")
    private String queueProductLog;

    @Value("${rabbitmq.product.routingkey}")
    private String routingKeyProductLog;

    @Bean
    public Queue criarFilaProductLog(){
        return QueueBuilder.nonDurable(queueProductLog).build();
    }

    @Bean
    public DirectExchange criarExchangeMarketPlace(){
        return ExchangeBuilder.directExchange(exchangeMarketPlace).build();
    }

    @Bean
    public Binding criarBindingProductLog(){
        return BindingBuilder.bind(criarFilaProductLog())
                .to(criarExchangeMarketPlace())
                .with(routingKeyProductLog);
    }

//    @Bean
//    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory){
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public ApplicationListener<ApplicationReadyEvent> inicializarRabbitAdmin(RabbitAdmin rabbitAdmin){
//        return event -> rabbitAdmin.initialize();
//    }


}
