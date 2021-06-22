package com.minhnk.query.message.evenbus;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvenBusMQConfig {

    public static final String QUEUE = "query_queue";
    public static final String TOPIC_EXCHANGE = "query_exchange";
    public static final String ROUTING_KEY = "query_key";

    @Bean
    public Queue evenBusQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange evenBusTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding evenBusBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter evenBusMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate evenBusMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(evenBusMessageConverter());
        return rabbitTemplate;
    }
}
