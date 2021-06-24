package com.minhnk.query.message.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EvenBusConsumerMQConfig {

    public static final String EVEN_BUS_PRODUCER_QUEUE = "even_bus_producer_queue";
    public static final String EVEN_BUS_PRODUCER_TOPIC_EXCHANGE = "even_bus_producer_topic";
    public static final String EVEN_BUS_PRODUCER_ROUTING_KEY = "even_bus_producer_key";

    @Bean
    @Primary
    public Queue postConsumerQueue(){
        return new Queue(EVEN_BUS_PRODUCER_QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange postConsumerTopicExchange(){
        return new TopicExchange(EVEN_BUS_PRODUCER_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding postConsumerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(EVEN_BUS_PRODUCER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter postConsumerMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate postConsumerMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(postConsumerMessageConverter());
        return rabbitTemplate;
    }
}
