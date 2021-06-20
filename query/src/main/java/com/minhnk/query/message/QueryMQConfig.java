package com.minhnk.query.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QueryMQConfig {

    public static final String QUEUE = "query_queue";
    public static final String TOPIC_EXCHANGE = "query_exchange";
    public static final String ROUTING_KEY = "query_key";

    @Bean
    @Primary
    public Queue queryQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange queryTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    @Primary
    public Binding queryBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    @Primary
    public MessageConverter queryMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public AmqpTemplate queryMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(queryMessageConverter());
        return rabbitTemplate;
    }
}
