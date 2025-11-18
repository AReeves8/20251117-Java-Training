package com.skillstorm.rabbitmqpublisher.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    
    /**
     * Can read from the enviornment using @Value and the ${} syntax
     *      @Value will inject the value into the parameter
     */
    @Value("${exchanges.fanout}")
    private String fanoutExchangeName;


    /**
     * creating the FanoutExchange 
     *      you can name your beans if you have multiple exchanges to worry about
     *      then just ask for those bean namews with @Qualifier
     */
    @Bean
    public Exchange fanout() {
        return new FanoutExchange(fanoutExchangeName);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        /**
         * convert our java objects into json
         *      Jackson is a very common object mapper/converter
         * 
         * rabbitmq will use this implicitly
         */
        return new Jackson2JsonMessageConverter();
    }
}
