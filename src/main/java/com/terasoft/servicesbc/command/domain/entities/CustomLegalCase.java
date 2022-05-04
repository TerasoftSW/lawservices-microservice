package com.terasoft.servicesbc.command.domain.entities;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import com.terasoft.servicesbc.command.domain.enums.MeetType;
import com.terasoft.servicesbc.contracts.commands.EditCustomLegalCase;
import com.terasoft.servicesbc.contracts.commands.RegisterCustomLegalCase;
import com.terasoft.servicesbc.contracts.events.CustomLegalCaseEdited;
import com.terasoft.servicesbc.contracts.events.CustomLegalCaseRegistered;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@DiscriminatorValue(value="2")
@Aggregate
public class CustomLegalCase extends LawService {
    private String startedAt;
    private String finishedAt;
    @Enumerated(EnumType.STRING)
    private MeetType meetType;
    private String linkZoom;

    public CustomLegalCase() {}

    @CommandHandler
    public CustomLegalCase(RegisterCustomLegalCase command) {
        apply(
                new CustomLegalCaseRegistered(
                        command.getCustomLegalCaseId(),
                        command.getTitle(),
                        command.getStartedAt(),
                        command.getFinishedAt(),
                        command.getMeetType(),
                        command.getLinkZoom()
                )
        );
    }

    @CommandHandler
    public void handle(EditCustomLegalCase command) {
        apply(
                new CustomLegalCaseEdited(
                        command.getCustomLegalCaseId(),
                        command.getState(),
                        command.getTitle(),
                        command.getStartedAt(),
                        command.getFinishedAt(),
                        command.getMeetType(),
                        command.getLinkZoom()
                )
        );
    }

    @EventSourcingHandler
    protected void on(CustomLegalCaseRegistered event) {
        lawServiceId = event.getCustomLegalCaseId();
        state = LawServiceState.INPROCESS;
        title = event.getTitle();
        startedAt = event.getStartedAt();
        finishedAt = event.getFinishedAt();
        meetType = event.getMeetType();
        linkZoom = event.getLinkZoom();
    }

    @EventSourcingHandler
    protected void on(CustomLegalCaseEdited event) {
        state = event.getState();
        title = event.getTitle();
        startedAt = event.getStartedAt();
        finishedAt = event.getFinishedAt();
        meetType = event.getMeetType();
        linkZoom = event.getLinkZoom();
    }
}
