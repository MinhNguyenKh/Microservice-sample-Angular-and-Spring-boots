package com.minhnk.post.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PostMQConfig {

    public static final String QUEUE = "post_queue";
    public static final String TOPIC_EXCHANGE = "post_exchange";
    public static final String ROUTING_KEY = "post_key";

    @Bean
    @Primary
    public Queue postQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange postTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    @Primary
    public Binding postBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    @Primary
    public MessageConverter postMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public AmqpTemplate postMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(postMessageConverter());
        return rabbitTemplate;
    }
}
