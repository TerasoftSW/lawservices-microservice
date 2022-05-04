package com.terasoft.servicesbc.command.application.validators;

import com.terasoft.servicesbc.command.application.dtos.request.EditCustomLegalCaseRequest;
import com.terasoft.servicesbc.command.domain.entities.CustomLegalCase;
import com.terasoft.servicesbc.common.application.Notification;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditCustomLegalCaseValidator {

    private final Repository<CustomLegalCase> customLegalCaseRepository;

    public EditCustomLegalCaseValidator(Repository<CustomLegalCase> customLegalCaseRepository) {
        this.customLegalCaseRepository = customLegalCaseRepository;
    }

    public Notification validate(EditCustomLegalCaseRequest editCustomLegalCaseRequest) {
        Notification notification = new Notification();
        String customLegalCaseId = editCustomLegalCaseRequest.getCustomLegalCaseId().trim();
        if (customLegalCaseId.isEmpty()) {
            notification.addError("Custom Legal Case id is required");
        }
        loadCustomLegalCaseAggregate(customLegalCaseId);
        String startedAt = editCustomLegalCaseRequest.getStartedAt().trim();
        if (startedAt.isEmpty()) {
            notification.addError("Custom Legal Case startedAt is required");
        }
        String finishedAt = editCustomLegalCaseRequest.getFinishedAt().trim();
        if (finishedAt.isEmpty()) {
            notification.addError("Custom Legal Case finishedAt is required");
        }
        String meetType = editCustomLegalCaseRequest.getMeetType();
        if (meetType.isEmpty()) {
            notification.addError("Custom Legal Case meetType is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadCustomLegalCaseAggregate(String customLegalCaseId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            customLegalCaseRepository.load(customLegalCaseId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex) {
            if (unitOfWork != null) {
                unitOfWork.rollback();
            }
        }
    }
}
