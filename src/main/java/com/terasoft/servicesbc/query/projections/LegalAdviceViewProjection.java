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
public class LegalAdviceViewProjection {
    private final LegalAdviceViewRepository legalAdviceViewRepository;

    public LegalAdviceViewProjection(LegalAdviceViewRepository legalAdviceViewRepository) {
        this.legalAdviceViewRepository = legalAdviceViewRepository;
    }

    @EventHandler
    public void on(LegalAdviceRegistered event, @Timestamp Instant timestamp) {
        LegalAdviceView legalAdviceView = new LegalAdviceView(event.getLegalAdviceId(), LawServiceState.INPROCESS.toString(), event.getTitle(), event.getDescription(), "");
        legalAdviceViewRepository.save(legalAdviceView);
    }

    @EventHandler
    public void on(LegalAdviceEdited event, @Timestamp Instant timestamp) {
        Optional<LegalAdviceView> legalAdviceViewOptional = legalAdviceViewRepository.findById(event.getLegalAdviceId().toString());
        if (legalAdviceViewOptional.isPresent()) {
            LegalAdviceView legalAdviceView = legalAdviceViewOptional.get();
            legalAdviceView.setState(event.getState().toString());
            legalAdviceView.setTitle(event.getTitle());
            legalAdviceView.setDescription(event.getDescription());
            legalAdviceView.setLegalResponse(event.getLegalResponse());
            legalAdviceViewRepository.save(legalAdviceView);
        }
    }
}
