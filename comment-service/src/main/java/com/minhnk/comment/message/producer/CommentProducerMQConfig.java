package com.minhnk.comment.message.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommentProducerMQConfig {

    public static final String COMMENT_PRODUCER_QUEUE = "comment_queue";
    public static final String COMMENT_PRODUCER_TOPIC_EXCHANGE = "comment_exchange";
    public static final String COMMENT_PRODUCER_ROUTING_KEY = "comment_key";

    @Bean
    public Queue commentProducerQueue(){
        return new Queue(COMMENT_PRODUCER_QUEUE);
    }

    @Bean
    public TopicExchange commentProducerTopicExchange(){
        return new TopicExchange(COMMENT_PRODUCER_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding commentProducerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(COMMENT_PRODUCER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter commentProducerMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate commentProducerMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(commentProducerMessageConverter());
        return rabbitTemplate;
    }
}
