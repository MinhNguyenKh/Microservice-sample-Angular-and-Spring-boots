package com.minhnk.evenbus.message.consumer.post;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConsumerMQConfig {

    public static final String POST_CONSUMER_QUEUE = "post_queue";
    public static final String POST_CONSUMER_TOPIC_EXCHANGE = "post_topic";
    public static final String POST_CONSUMER_ROUTING_KEY = "post_key";

    @Bean
    public Queue postConsumerQueue(){
        return new Queue(POST_CONSUMER_QUEUE);
    }

    @Bean
    public TopicExchange postConsumerTopicExchange(){
        return new TopicExchange(POST_CONSUMER_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding postConsumerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(POST_CONSUMER_ROUTING_KEY);
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
