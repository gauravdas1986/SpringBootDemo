package com.cogni.apartment.rabbit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final ConnectionFactory connectionFactory;
    public static final String ROUTING_KEY = "apt-queue";
    public static final String EXCHANGE = "apt_queue_exchange";

    @Autowired
    public RabbitConfig(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }
    
    @Bean
    TopicExchange exchange() {
     return new TopicExchange(EXCHANGE);
    }

    @Bean
    public RabbitTemplate template() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        rabbitTemplate.setRoutingKey(ROUTING_KEY);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public Queue queue() {
        return new Queue("apt-queue", false);
    }
    
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
     return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}