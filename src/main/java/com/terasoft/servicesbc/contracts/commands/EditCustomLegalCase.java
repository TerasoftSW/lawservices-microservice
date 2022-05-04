package com.terasoft.servicesbc.contracts.commands;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import com.terasoft.servicesbc.command.domain.enums.MeetType;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class EditCustomLegalCase {
    @TargetAggregateIdentifier
    private String customLegalCaseId;
    private LawServiceState state;
    private String title;
    private String startedAt;
    private String finishedAt;
    private MeetType meetType;
    private String linkZoom;

}
