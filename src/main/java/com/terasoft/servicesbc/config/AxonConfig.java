package com.terasoft.servicesbc.config;

import com.terasoft.servicesbc.command.domain.entities.CustomLegalCase;
import com.terasoft.servicesbc.command.domain.entities.LegalAdvice;
import com.thoughtworks.xstream.XStream;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[] {
                "com.terasoft.servicesbccontracts.**"
        });
        return xStream;
    }
    @Bean
    public Repository<CustomLegalCase> eventSourcingCustomLegalCaseRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(CustomLegalCase.class)
                .eventStore(eventStore)
                .build();
    }
    @Bean
    public Repository<LegalAdvice> eventSourcingLegalAdviceRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(LegalAdvice.class)
                .eventStore(eventStore)
                .build();
    }
}
