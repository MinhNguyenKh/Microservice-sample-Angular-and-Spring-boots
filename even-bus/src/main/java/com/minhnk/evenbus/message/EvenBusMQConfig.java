package com.minhnk.evenbus.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EvenBusMQConfig {

    public static final String QUEUE = "even_bus_queue";
    public static final String TOPIC_EXCHANGE = "even_bus_exchange";
    public static final String ROUTING_KEY = "even_bus_key";

    @Bean
    @Primary
    public Queue evenBusQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange evenBusTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    @Primary
    public Binding evenBusBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    @Primary
    public MessageConverter evenBusMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public AmqpTemplate evenBusMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(evenBusMessageConverter());
        return rabbitTemplate;
    }
}
