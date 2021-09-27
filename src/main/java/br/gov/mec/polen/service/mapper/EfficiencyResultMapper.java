package br.gov.mec.polen.service.mapper;

import br.gov.mec.polen.domain.*;
import br.gov.mec.polen.service.dto.EfficiencyResultDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EfficiencyResult} and its DTO {@link EfficiencyResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EfficiencyResultMapper extends EntityMapper<EfficiencyResultDTO, EfficiencyResult> {}
