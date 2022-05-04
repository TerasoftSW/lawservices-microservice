package com.terasoft.servicesbc.command.application.validators;

import com.terasoft.servicesbc.command.application.dtos.request.EditLegalAdviceRequest;
import com.terasoft.servicesbc.command.domain.entities.LegalAdvice;
import com.terasoft.servicesbc.common.application.Notification;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditLegalAdviceValidator {

    private final Repository<LegalAdvice> legalAdviceRepository;

    public EditLegalAdviceValidator(Repository<LegalAdvice> legalAdviceRepository) {
        this.legalAdviceRepository = legalAdviceRepository;
    }

    public Notification validate(EditLegalAdviceRequest editLegalAdviceRequest) {
        Notification notification = new Notification();
        String legalAdviceId = editLegalAdviceRequest.getLegalAdviceId().trim();
        if (legalAdviceId.isEmpty()) {
            notification.addError("Legal Advice id is required");
        }
        loadLegalAdviceAggregate(legalAdviceId);
        String legalResponse = editLegalAdviceRequest.getLegalResponse();
        if (legalResponse.isEmpty()) {
            notification.addError("Legal Advice legalResponse is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadLegalAdviceAggregate(String legalAdviceId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            legalAdviceRepository.load(legalAdviceId);
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
