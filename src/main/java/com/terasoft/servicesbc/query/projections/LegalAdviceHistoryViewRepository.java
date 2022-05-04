package com.terasoft.servicesbc.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalAdviceHistoryViewRepository extends JpaRepository<LegalAdviceHistoryView, String> {
    @Query(value = "SELECT * FROM legal_advice_history_view WHERE legal_advice_history_id = (SELECT MAX(legal_advice_history_id) FROM legal_advice_history_view WHERE legal_advice_id = :legalAdviceId)", nativeQuery = true)
    Optional<LegalAdviceHistoryView> getLastByLegalAdviceId(String legalAdviceId);

    @Query(value = "SELECT * FROM legal_advice_history_view WHERE legal_advice_id = :legalAdviceId", nativeQuery = true)
    List<LegalAdviceHistoryView> getHistoryByLegalAdviceId(String legalAdviceId);
}
