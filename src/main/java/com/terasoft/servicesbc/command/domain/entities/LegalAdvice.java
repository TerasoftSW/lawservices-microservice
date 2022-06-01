package com.terasoft.servicesbc.command.domain.entities;

import com.terasoft.common.domain.enums.LawServiceState;
import com.terasoft.servicesbccontracts.commands.EditLegalAdvice;
import com.terasoft.servicesbccontracts.commands.RegisterLegalAdvice;
import com.terasoft.servicesbccontracts.events.LegalAdviceEdited;
import com.terasoft.servicesbccontracts.events.LegalAdviceRegistered;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@DiscriminatorValue(value="1")
@Aggregate
public class LegalAdvice extends LawService {

    private String description;

    private String legalResponse;

    public LegalAdvice() {}

    @CommandHandler
    public LegalAdvice(RegisterLegalAdvice command) {
        apply(
                new LegalAdviceRegistered(
                        command.getLegalAdviceId(),
                        command.getTitle(),
                        command.getDescription()
                )
        );
    }

    @CommandHandler
    public void handle(EditLegalAdvice command) {
        apply(
                new LegalAdviceEdited(
                        command.getLegalAdviceId(),
                        command.getState(),
                        command.getTitle(),
                        command.getDescription(),
                        command.getLegalResponse()
                )
        );
    }

    @EventSourcingHandler
    protected void on(LegalAdviceRegistered event) {
        lawServiceId = event.getLegalAdviceId();
        state = LawServiceState.INPROCESS;
        title = event.getTitle();
        description = event.getDescription();
    }

    @EventSourcingHandler
    protected void on(LegalAdviceEdited event) {
        state = event.getState();
        title = event.getTitle();
        description = event.getDescription();
        legalResponse = event.getLegalResponse();
    }
}
