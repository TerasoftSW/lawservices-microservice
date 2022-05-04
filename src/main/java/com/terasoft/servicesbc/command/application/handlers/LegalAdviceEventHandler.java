package com.terasoft.servicesbc.command.application.handlers;

import com.terasoft.servicesbc.contracts.events.LegalAdviceEdited;
import com.terasoft.servicesbc.contracts.events.LegalAdviceRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class LegalAdviceEventHandler {
    public LegalAdviceEventHandler() {}

    @EventHandler
    public void on(LegalAdviceRegistered event) {}

    @EventHandler
    public void on(LegalAdviceEdited event) {}
}