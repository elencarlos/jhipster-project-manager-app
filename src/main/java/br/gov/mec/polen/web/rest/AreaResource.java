package br.gov.mec.polen.web.rest;

import br.gov.mec.polen.repository.AreaRepository;
import br.gov.mec.polen.service.AreaService;
import br.gov.mec.polen.service.dto.AreaDTO;
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
 * REST controller for managing {@link br.gov.mec.polen.domain.Area}.
 */
@RestController
@RequestMapping("/api")
public class AreaResource {

    private final Logger log = LoggerFactory.getLogger(AreaResource.class);

    private final AreaService areaService;

    private final AreaRepository areaRepository;

    public AreaResource(AreaService areaService, AreaRepository areaRepository) {
        this.areaService = areaService;
        this.areaRepository = areaRepository;
    }

    /**
     * {@code GET  /areas} : get all the areas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of areas in body.
     */
    @GetMapping("/areas")
    public List<AreaDTO> getAllAreas() {
        log.debug("REST request to get all Areas");
        return areaService.findAll();
    }

    /**
     * {@code GET  /areas/:id} : get the "id" area.
     *
     * @param id the id of the areaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the areaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/areas/{id}")
    public ResponseEntity<AreaDTO> getArea(@PathVariable Long id) {
        log.debug("REST request to get Area : {}", id);
        Optional<AreaDTO> areaDTO = areaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(areaDTO);
    }
}
