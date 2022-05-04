package com.terasoft.servicesbc.command.application.validators;

import com.terasoft.servicesbc.command.application.dtos.request.RegisterLegalAdviceRequest;
import com.terasoft.servicesbc.common.application.Notification;
import com.terasoft.servicesbc.query.projections.LegalAdviceViewRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterLegalAdviceValidator {

    public RegisterLegalAdviceValidator(LegalAdviceViewRepository legalAdviceRepository) { }

    public Notification validate(RegisterLegalAdviceRequest registerLegalAdviceRequest) {
        Notification notification = new Notification();
        String title = registerLegalAdviceRequest.getTitle() != null ? registerLegalAdviceRequest.getTitle().trim() : "";
        if (title.isEmpty()) {
            notification.addError("Legal Advice title is required");
        }
        String description = registerLegalAdviceRequest.getDescription() != null ? registerLegalAdviceRequest.getDescription().trim() : "";
        if (description.isEmpty()) {
            notification.addError("Legal Advice description is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
