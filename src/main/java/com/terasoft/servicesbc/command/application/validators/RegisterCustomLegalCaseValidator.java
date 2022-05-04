package com.terasoft.servicesbc.command.application.validators;

import com.terasoft.servicesbc.command.application.dtos.request.RegisterCustomLegalCaseRequest;
import com.terasoft.servicesbc.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterCustomLegalCaseValidator {

    public RegisterCustomLegalCaseValidator() {}

    public Notification validate(RegisterCustomLegalCaseRequest registerCustomLegalCaseRequest) {
        Notification notification = new Notification();
        String title = registerCustomLegalCaseRequest.getTitle().trim();
        if (title.isEmpty()) {
            notification.addError("Custom Legal Case title is required");
        }
        String startedAt = registerCustomLegalCaseRequest.getStartedAt().trim();
        if (startedAt.isEmpty()) {
            notification.addError("Custom Legal Case startedAt is required");
        }
        String finishedAt = registerCustomLegalCaseRequest.getFinishedAt().trim();
        if (finishedAt.isEmpty()) {
            notification.addError("Custom Legal Case finishedAt is required");
        }
        String meetType = registerCustomLegalCaseRequest.getMeetType();
        if (meetType.isEmpty()) {
            notification.addError("Custom Legal Case meetType is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
