package br.gov.mec.polen.service;

import br.gov.mec.polen.domain.*; // for static metamodels
import br.gov.mec.polen.domain.EfficiencyResult;
import br.gov.mec.polen.repository.EfficiencyResultRepository;
import br.gov.mec.polen.service.criteria.EfficiencyResultCriteria;
import br.gov.mec.polen.service.dto.EfficiencyResultDTO;
import br.gov.mec.polen.service.mapper.EfficiencyResultMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link EfficiencyResult} entities in the database.
 * The main input is a {@link EfficiencyResultCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EfficiencyResultDTO} or a {@link Page} of {@link EfficiencyResultDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EfficiencyResultQueryService extends QueryService<EfficiencyResult> {

    private final Logger log = LoggerFactory.getLogger(EfficiencyResultQueryService.class);

    private final EfficiencyResultRepository efficiencyResultRepository;

    private final EfficiencyResultMapper efficiencyResultMapper;

    public EfficiencyResultQueryService(
        EfficiencyResultRepository efficiencyResultRepository,
        EfficiencyResultMapper efficiencyResultMapper
    ) {
        this.efficiencyResultRepository = efficiencyResultRepository;
        this.efficiencyResultMapper = efficiencyResultMapper;
    }

    /**
     * Return a {@link List} of {@link EfficiencyResultDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EfficiencyResultDTO> findByCriteria(EfficiencyResultCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EfficiencyResult> specification = createSpecification(criteria);
        return efficiencyResultMapper.toDto(efficiencyResultRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EfficiencyResultDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EfficiencyResultDTO> findByCriteria(EfficiencyResultCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EfficiencyResult> specification = createSpecification(criteria);
        return efficiencyResultRepository.findAll(specification, page).map(efficiencyResultMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EfficiencyResultCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EfficiencyResult> specification = createSpecification(criteria);
        return efficiencyResultRepository.count(specification);
    }

    /**
     * Function to convert {@link EfficiencyResultCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EfficiencyResult> createSpecification(EfficiencyResultCriteria criteria) {
        Specification<EfficiencyResult> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EfficiencyResult_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), EfficiencyResult_.name));
            }
        }
        return specification;
    }
}
