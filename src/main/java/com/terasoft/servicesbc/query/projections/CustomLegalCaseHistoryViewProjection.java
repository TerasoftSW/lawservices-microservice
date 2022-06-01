package com.terasoft.servicesbc.query.projections;

import com.terasoft.common.domain.enums.LawServiceState;
import com.terasoft.servicesbccontracts.events.CustomLegalCaseEdited;
import com.terasoft.servicesbccontracts.events.CustomLegalCaseRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class CustomLegalCaseHistoryViewProjection {
    private final CustomLegalCaseHistoryViewRepository customLegalCaseHistoryViewRepository;

    public CustomLegalCaseHistoryViewProjection(CustomLegalCaseHistoryViewRepository customLegalCaseHistoryViewRepository) {
        this.customLegalCaseHistoryViewRepository = customLegalCaseHistoryViewRepository;
    }

    @EventHandler
    public void on(CustomLegalCaseRegistered event, @Timestamp Instant timestamp) {
        CustomLegalCaseHistoryView customLegalCaseHistoryView = new CustomLegalCaseHistoryView(event.getCustomLegalCaseId(), LawServiceState.INPROCESS.toString(), event.getTitle(), event.getStartedAt(), event.getFinishedAt(), event.getMeetType().toString(), event.getLinkZoom());
        customLegalCaseHistoryViewRepository.save(customLegalCaseHistoryView);
    }

    @EventHandler
    public void on(CustomLegalCaseEdited event, @Timestamp Instant timestamp) {
        Optional<CustomLegalCaseHistoryView> customLegalCaseHistoryViewOptional = customLegalCaseHistoryViewRepository.getLastByCustomLegalCaseId(event.getCustomLegalCaseId().toString());
        if (customLegalCaseHistoryViewOptional.isPresent()) {
            CustomLegalCaseHistoryView customLegalCaseHistoryView = customLegalCaseHistoryViewOptional.get();
            customLegalCaseHistoryView = new CustomLegalCaseHistoryView(customLegalCaseHistoryView);
            customLegalCaseHistoryView.setState(event.getState().toString());
            customLegalCaseHistoryView.setTitle(event.getTitle());
            customLegalCaseHistoryView.setStartedAt(event.getStartedAt());
            customLegalCaseHistoryView.setFinishedAt(event.getFinishedAt());
            customLegalCaseHistoryView.setMeetType(event.getMeetType().toString());
            customLegalCaseHistoryView.setLinkZoom(event.getLinkZoom());
            customLegalCaseHistoryViewRepository.save(customLegalCaseHistoryView);
        }
    }
}
