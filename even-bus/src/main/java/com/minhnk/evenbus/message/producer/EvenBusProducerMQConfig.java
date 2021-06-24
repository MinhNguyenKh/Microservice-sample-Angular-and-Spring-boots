package com.minhnk.evenbus.message.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EvenBusProducerMQConfig {

    public static final String EVEN_BUS_PRODUCER_QUEUE = "even_bus_producer_queue";
    public static final String EVEN_BUS_PRODUCER_TOPIC_EXCHANGE = "even_bus_producer_topic";
    public static final String EVEN_BUS_PRODUCER_ROUTING_KEY = "even_bus_producer_key";

    @Bean
    @Primary
    public Queue evenBusProducerQueue(){
        return new Queue(EVEN_BUS_PRODUCER_QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange evenBusProducerTopicExchange(){
        return new TopicExchange(EVEN_BUS_PRODUCER_TOPIC_EXCHANGE);
    }

    @Bean
    @Primary
    public Binding evenBusProducerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(EVEN_BUS_PRODUCER_ROUTING_KEY);
    }

    @Bean
    @Primary
    public MessageConverter evenBusProducerMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public AmqpTemplate evenBusProducerMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(evenBusProducerMessageConverter());
        return rabbitTemplate;
    }
}
