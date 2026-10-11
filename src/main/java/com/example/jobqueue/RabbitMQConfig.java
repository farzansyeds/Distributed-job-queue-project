package com.example.jobqueue;

import org.springframework.amqp.core.Queue;
// We now import the new JacksonJsonMessageConverter without the "2"
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "default_job_queue";

    @Bean
    public Queue jobQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        // We instantiate the new class here
        return new JacksonJsonMessageConverter();
    }
}


//package com.example.jobqueue;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class RabbitMQConfig {
//    public static final String QUEUE_NAME = "default_job_queue";
//
//    @Bean
//    public Queue jobQueue(){
//        return new Queue(QUEUE_NAME,true);
//    }
//
//}
