package org.icewind.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ye Jianyu
 * @date 2018/5/23
 */
@Configuration
public class AmqpConfig {

//    @Value("${spring.rabbitmq.host}")
//    private String mqRabbitHost;
//
//    @Value("${spring.rabbitmq.port}")
//    private int mqRabbitPort;
//
//    @Value("${spring.rabbitmq.username}")
//    private String mqRabbitUserName;
//
//    @Value("${spring.rabbitmq.password}")
//    private String mqRabbitPassword;
//
//    @Value("${spring.rabbitmq.virtualHost}")
//    private String mqRabbitVirtualHost;

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.mqRabbitHost,this.mqRabbitPort);
//
//        connectionFactory.setUsername(this.mqRabbitUserName);
//        connectionFactory.setPassword(this.mqRabbitPassword);
//        connectionFactory.setVirtualHost(this.mqRabbitVirtualHost);
//        connectionFactory.setPublisherConfirms(true);
//
//        return connectionFactory;
//    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> System.out.printf("correlationData: %s; ack: %s; cause: %s", correlationData,b, s));
        return rabbitTemplate;
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello.queue");
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("topic.queue");
    }


    @Bean
    public Queue userQueue() {
        return new Queue("user.queue");
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("hello.exchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(defaultExchange()).with("hello.key");
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("*.key");
    }

    @Bean
    public Binding topic2Binding() {
        return BindingBuilder.bind(helloQueue()).to(topicExchange()).with("topic.*");
    }

    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(defaultExchange()).with("user.key");
    }
}
