package br.gov.mec.polen.service;

import br.gov.mec.polen.domain.EfficiencyResult;
import br.gov.mec.polen.repository.EfficiencyResultRepository;
import br.gov.mec.polen.service.dto.EfficiencyResultDTO;
import br.gov.mec.polen.service.mapper.EfficiencyResultMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    private final EfficiencyResultMapper efficiencyResultMapper;

    public EfficiencyResultService(EfficiencyResultRepository efficiencyResultRepository, EfficiencyResultMapper efficiencyResultMapper) {
        this.efficiencyResultRepository = efficiencyResultRepository;
        this.efficiencyResultMapper = efficiencyResultMapper;
    }

    /**
     * Save a efficiencyResult.
     *
     * @param efficiencyResultDTO the entity to save.
     * @return the persisted entity.
     */
    public EfficiencyResultDTO save(EfficiencyResultDTO efficiencyResultDTO) {
        log.debug("Request to save EfficiencyResult : {}", efficiencyResultDTO);
        EfficiencyResult efficiencyResult = efficiencyResultMapper.toEntity(efficiencyResultDTO);
        efficiencyResult = efficiencyResultRepository.save(efficiencyResult);
        return efficiencyResultMapper.toDto(efficiencyResult);
    }

    /**
     * Partially update a efficiencyResult.
     *
     * @param efficiencyResultDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EfficiencyResultDTO> partialUpdate(EfficiencyResultDTO efficiencyResultDTO) {
        log.debug("Request to partially update EfficiencyResult : {}", efficiencyResultDTO);

        return efficiencyResultRepository
            .findById(efficiencyResultDTO.getId())
            .map(existingEfficiencyResult -> {
                efficiencyResultMapper.partialUpdate(existingEfficiencyResult, efficiencyResultDTO);

                return existingEfficiencyResult;
            })
            .map(efficiencyResultRepository::save)
            .map(efficiencyResultMapper::toDto);
    }

    /**
     * Get all the efficiencyResults.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EfficiencyResultDTO> findAll() {
        log.debug("Request to get all EfficiencyResults");
        return efficiencyResultRepository
            .findAll()
            .stream()
            .map(efficiencyResultMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one efficiencyResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EfficiencyResultDTO> findOne(Long id) {
        log.debug("Request to get EfficiencyResult : {}", id);
        return efficiencyResultRepository.findById(id).map(efficiencyResultMapper::toDto);
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
