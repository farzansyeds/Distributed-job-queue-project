package com.example.jobqueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;

    public ProducerController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> submitJob(@RequestBody Map<String,Object> payload){
        // 1. Generate a unique tracking ID for the job
        String jobId = UUID.randomUUID().toString();

        // 2. Add the ID to the payload so the worker knows which job it is
        payload.put("job_id", jobId);

        // 3. Send the message to RabbitMQ
        // We tell it the exact queue name we defined in our config
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, payload);

        // 4. Return an immediate 202 Accepted response
        return ResponseEntity.accepted().body("Job Accepted. Tracking ID: " + jobId);
    }

}
