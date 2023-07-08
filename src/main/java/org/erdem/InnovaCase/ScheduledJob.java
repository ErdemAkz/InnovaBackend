package org.erdem.InnovaCase;


import org.erdem.InnovaCase.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {

    @Autowired
    UserController userController;

    @Scheduled(fixedRate = 30000) // Runs every 5 seconds
    public void runJob() {
        // Job logic to be executed periodically
        System.out.println("Scheduled job executed!");

        userController.scheduled_job_5();
    }
}