package br.gov.mec.polen.web.rest;

import br.gov.mec.polen.repository.EfficiencyResultRepository;
import br.gov.mec.polen.service.EfficiencyResultQueryService;
import br.gov.mec.polen.service.EfficiencyResultService;
import br.gov.mec.polen.service.criteria.EfficiencyResultCriteria;
import br.gov.mec.polen.service.dto.EfficiencyResultDTO;
import br.gov.mec.polen.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link br.gov.mec.polen.domain.EfficiencyResult}.
 */
@RestController
@RequestMapping("/api")
public class EfficiencyResultResource {

    private final Logger log = LoggerFactory.getLogger(EfficiencyResultResource.class);

    private final EfficiencyResultService efficiencyResultService;

    private final EfficiencyResultRepository efficiencyResultRepository;

    private final EfficiencyResultQueryService efficiencyResultQueryService;

    public EfficiencyResultResource(
        EfficiencyResultService efficiencyResultService,
        EfficiencyResultRepository efficiencyResultRepository,
        EfficiencyResultQueryService efficiencyResultQueryService
    ) {
        this.efficiencyResultService = efficiencyResultService;
        this.efficiencyResultRepository = efficiencyResultRepository;
        this.efficiencyResultQueryService = efficiencyResultQueryService;
    }

    /**
     * {@code GET  /efficiency-results} : get all the efficiencyResults.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of efficiencyResults in body.
     */
    @GetMapping("/efficiency-results")
    public ResponseEntity<List<EfficiencyResultDTO>> getAllEfficiencyResults(EfficiencyResultCriteria criteria) {
        log.debug("REST request to get EfficiencyResults by criteria: {}", criteria);
        List<EfficiencyResultDTO> entityList = efficiencyResultQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /efficiency-results/count} : count all the efficiencyResults.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/efficiency-results/count")
    public ResponseEntity<Long> countEfficiencyResults(EfficiencyResultCriteria criteria) {
        log.debug("REST request to count EfficiencyResults by criteria: {}", criteria);
        return ResponseEntity.ok().body(efficiencyResultQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /efficiency-results/:id} : get the "id" efficiencyResult.
     *
     * @param id the id of the efficiencyResultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the efficiencyResultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/efficiency-results/{id}")
    public ResponseEntity<EfficiencyResultDTO> getEfficiencyResult(@PathVariable Long id) {
        log.debug("REST request to get EfficiencyResult : {}", id);
        Optional<EfficiencyResultDTO> efficiencyResultDTO = efficiencyResultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(efficiencyResultDTO);
    }
}
