package com.terasoft.servicesbc.command.application.handlers;

import com.terasoft.servicesbc.contracts.events.CustomLegalCaseEdited;
import com.terasoft.servicesbc.contracts.events.CustomLegalCaseRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLegalCaseEventHandler {
    public CustomLegalCaseEventHandler() {}

    @EventHandler
    public void on(CustomLegalCaseRegistered event) {}

    @EventHandler
    public void on(CustomLegalCaseEdited event) {}
}
