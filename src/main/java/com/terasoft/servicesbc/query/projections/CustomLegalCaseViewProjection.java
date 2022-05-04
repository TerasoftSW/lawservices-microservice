package com.terasoft.servicesbc.query.projections;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import com.terasoft.servicesbc.contracts.events.CustomLegalCaseEdited;
import com.terasoft.servicesbc.contracts.events.CustomLegalCaseRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class CustomLegalCaseViewProjection {
    private final CustomLegalCaseViewRepository customLegalCaseViewRepository;

    public CustomLegalCaseViewProjection(CustomLegalCaseViewRepository customLegalCaseViewRepository) {
        this.customLegalCaseViewRepository = customLegalCaseViewRepository;
    }

    @EventHandler
    public void on(CustomLegalCaseRegistered event, @Timestamp Instant timestamp) {
        CustomLegalCaseView customLegalCaseView = new CustomLegalCaseView(event.getCustomLegalCaseId(), LawServiceState.INPROCESS.toString(), event.getTitle(), event.getStartedAt(), event.getFinishedAt(), event.getMeetType().toString(), event.getLinkZoom());
        customLegalCaseViewRepository.save(customLegalCaseView);
    }

    @EventHandler
    public void on(CustomLegalCaseEdited event, @Timestamp Instant timestamp) {
        Optional<CustomLegalCaseView> customLegalCaseViewOptional = customLegalCaseViewRepository.findById(event.getCustomLegalCaseId().toString());
        if (customLegalCaseViewOptional.isPresent()) {
            CustomLegalCaseView customLegalCaseView = customLegalCaseViewOptional.get();
            customLegalCaseView.setState(event.getState().toString());
            customLegalCaseView.setTitle(event.getTitle());
            customLegalCaseView.setStartedAt(event.getStartedAt());
            customLegalCaseView.setFinishedAt(event.getFinishedAt());
            customLegalCaseView.setMeetType(event.getMeetType().toString());
            customLegalCaseView.setLinkZoom(event.getLinkZoom());
            customLegalCaseViewRepository.save(customLegalCaseView);
        }
    }
}
