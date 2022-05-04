package com.terasoft.servicesbc.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterLegalAdvice {
    @TargetAggregateIdentifier
    private String legalAdviceId;
    private String title;
    private String description;
}
