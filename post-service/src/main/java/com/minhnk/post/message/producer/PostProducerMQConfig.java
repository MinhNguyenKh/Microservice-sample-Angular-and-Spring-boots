package com.minhnk.post.message.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProducerMQConfig {

    public static final String POST_PRODUCER_QUEUE = "post_queue";
    public static final String POST_PRODUCER_TOPIC_EXCHANGE = "post_topic";
    public static final String POST_PRODUCER_ROUTING_KEY = "post_key";

    @Bean
    public Queue postProducerQueue(){
        return new Queue(POST_PRODUCER_QUEUE);
    }

    @Bean
    public TopicExchange postProducerTopicExchange(){
        return new TopicExchange(POST_PRODUCER_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding postProducerBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(POST_PRODUCER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter postProducerMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate postProducerMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(postProducerMessageConverter());
        return rabbitTemplate;
    }
}
