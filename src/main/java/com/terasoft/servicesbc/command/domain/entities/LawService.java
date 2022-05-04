package com.terasoft.servicesbc.command.domain.entities;

import com.terasoft.servicesbc.command.domain.enums.LawServiceState;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "service_type_id",
        columnDefinition = "TINYINT(1)"
)
@Aggregate
public class LawService {
    @AggregateIdentifier
    protected String lawServiceId;

    protected LawServiceState state;

    protected String title;

    public LawService() {}
}
