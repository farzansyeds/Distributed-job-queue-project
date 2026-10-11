package com.example.jobqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JobWorker {

    // This annotation tells Spring to listen to this specific queue in the background
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processJob(Map<String, Object> payload) {
        System.out.println("\n[+] Worker claimed a new job!");
        System.out.println("[*] Job Details: " + payload);

        try {
            // Simulate a time-consuming task (e.g., rendering video, sending emails)
            System.out.println("[-] Processing...");
            Thread.sleep(5000); // Pauses this specific worker thread for 5 seconds

            System.out.println("[✓] Job " + payload.get("job_id") + " completed successfully.\n");

        } catch (InterruptedException e) {
            System.err.println("[x] Worker was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

