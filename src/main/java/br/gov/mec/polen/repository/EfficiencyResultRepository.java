package br.gov.mec.polen.repository;

import br.gov.mec.polen.domain.EfficiencyResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EfficiencyResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EfficiencyResultRepository extends JpaRepository<EfficiencyResult, Long>, JpaSpecificationExecutor<EfficiencyResult> {}
