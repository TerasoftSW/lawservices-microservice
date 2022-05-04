package com.terasoft.servicesbc.contracts.events;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import lombok.Value;

@Value
public class LegalAdviceEdited {
    private String legalAdviceId;
    private LawServiceState state;
    private String title;
    private String description;
    private String legalResponse;
}
