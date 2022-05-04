package com.terasoft.servicesbc.query.projections;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import com.terasoft.servicesbc.contracts.events.LegalAdviceEdited;
import com.terasoft.servicesbc.contracts.events.LegalAdviceRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class LegalAdviceHistoryViewProjection {
    private final LegalAdviceHistoryViewRepository legalAdviceHistoryViewRepository;

    public LegalAdviceHistoryViewProjection(LegalAdviceHistoryViewRepository legalAdviceHistoryViewRepository) {
        this.legalAdviceHistoryViewRepository = legalAdviceHistoryViewRepository;
    }

    @EventHandler
    public void on(LegalAdviceRegistered event, @Timestamp Instant timestamp) {
        LegalAdviceHistoryView legalAdviceHistoryView = new LegalAdviceHistoryView(event.getLegalAdviceId(), LawServiceState.INPROCESS.toString(), event.getTitle(), event.getDescription(), "");
        legalAdviceHistoryViewRepository.save(legalAdviceHistoryView);
    }

    @EventHandler
    public void on(LegalAdviceEdited event, @Timestamp Instant timestamp) {
        Optional<LegalAdviceHistoryView> legalAdviceHistoryViewOptional = legalAdviceHistoryViewRepository.getLastByLegalAdviceId(event.getLegalAdviceId().toString());
        if (legalAdviceHistoryViewOptional.isPresent()) {
            LegalAdviceHistoryView legalAdviceHistoryView = legalAdviceHistoryViewOptional.get();
            legalAdviceHistoryView = new LegalAdviceHistoryView(legalAdviceHistoryView);
            legalAdviceHistoryView.setState(event.getState().toString());
            legalAdviceHistoryView.setTitle(event.getTitle());
            legalAdviceHistoryView.setDescription(event.getDescription());
            legalAdviceHistoryView.setLegalResponse(event.getLegalResponse());
            legalAdviceHistoryViewRepository.save(legalAdviceHistoryView);
        }
    }
}
