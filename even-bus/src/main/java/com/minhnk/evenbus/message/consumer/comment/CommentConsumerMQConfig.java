package com.minhnk.evenbus.message.consumer.comment;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConsumerMQConfig {

    public static final String COMMENT_CONSUMER_QUEUE = "comment_queue";
    public static final String COMMENT_CONSUMER_TOPIC_EXCHANGE = "comment_topic";
    public static final String COMMENT_CONSUMER_ROUTING_KEY = "comment_key";

    @Bean
    public Queue commentConsumerQueue(){
        return new Queue(COMMENT_CONSUMER_QUEUE);
    }

    @Bean
    public TopicExchange commentConsumerTopicExchange(){
        return new TopicExchange(COMMENT_CONSUMER_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding commentConsumerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(COMMENT_CONSUMER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter commentConsumerMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate commentConsumerMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(commentConsumerMessageConverter());
        return rabbitTemplate;
    }
}
