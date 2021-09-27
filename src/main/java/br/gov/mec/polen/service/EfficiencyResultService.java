package br.gov.mec.polen.service;

import br.gov.mec.polen.domain.EfficiencyResult;
import br.gov.mec.polen.repository.EfficiencyResultRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EfficiencyResult}.
 */
@Service
@Transactional
public class EfficiencyResultService {

    private final Logger log = LoggerFactory.getLogger(EfficiencyResultService.class);

    private final EfficiencyResultRepository efficiencyResultRepository;

    public EfficiencyResultService(EfficiencyResultRepository efficiencyResultRepository) {
        this.efficiencyResultRepository = efficiencyResultRepository;
    }

    /**
     * Save a efficiencyResult.
     *
     * @param efficiencyResult the entity to save.
     * @return the persisted entity.
     */
    public EfficiencyResult save(EfficiencyResult efficiencyResult) {
        log.debug("Request to save EfficiencyResult : {}", efficiencyResult);
        return efficiencyResultRepository.save(efficiencyResult);
    }

    /**
     * Partially update a efficiencyResult.
     *
     * @param efficiencyResult the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EfficiencyResult> partialUpdate(EfficiencyResult efficiencyResult) {
        log.debug("Request to partially update EfficiencyResult : {}", efficiencyResult);

        return efficiencyResultRepository
            .findById(efficiencyResult.getId())
            .map(existingEfficiencyResult -> {
                if (efficiencyResult.getName() != null) {
                    existingEfficiencyResult.setName(efficiencyResult.getName());
                }

                return existingEfficiencyResult;
            })
            .map(efficiencyResultRepository::save);
    }

    /**
     * Get all the efficiencyResults.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EfficiencyResult> findAll() {
        log.debug("Request to get all EfficiencyResults");
        return efficiencyResultRepository.findAll();
    }

    /**
     * Get one efficiencyResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EfficiencyResult> findOne(Long id) {
        log.debug("Request to get EfficiencyResult : {}", id);
        return efficiencyResultRepository.findById(id);
    }

    /**
     * Delete the efficiencyResult by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EfficiencyResult : {}", id);
        efficiencyResultRepository.deleteById(id);
    }
}
