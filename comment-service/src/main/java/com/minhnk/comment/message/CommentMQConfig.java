package com.minhnk.comment.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommentMQConfig {

    public static final String QUEUE = "comment_queue";
    public static final String TOPIC_EXCHANGE = "comment_exchange";
    public static final String ROUTING_KEY = "comment_key";

    @Bean
    @Primary
    public Queue commentQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    @Primary
    public TopicExchange commentTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    @Primary
    public Binding commentBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    @Primary
    public MessageConverter commentMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public AmqpTemplate commentMsgTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(commentMessageConverter());
        return rabbitTemplate;
    }
}
