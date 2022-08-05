package com.example.crud_todolist.scheduler;

import com.example.crud_todolist.config.AdminConfig;
import com.example.crud_todolist.domain.Mail;
import com.example.crud_todolist.repository.TaskRepository;
import com.example.crud_todolist.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {

        long tasksAmount = taskRepository.count();

        simpleEmailService.send(Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message(generateMessage(tasksAmount))
                .build());

    }

    private String generateMessage(long tasksAmount){
        return tasksAmount == 1 ? "Currently in database you got 1 task" : "Currently in database you got: " + tasksAmount + " tasks";
    }

}
