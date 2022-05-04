package com.terasoft.servicesbc.query.projections;

import com.terasoft.servicesbc.command.domain.entities.LegalAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LegalAdviceViewRepository extends JpaRepository<LegalAdviceView, String> {
}
