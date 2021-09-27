package br.gov.mec.polen.service.mapper;

import br.gov.mec.polen.domain.*;
import br.gov.mec.polen.service.dto.AreaDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {
    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Set<AreaDTO> toDtoNameSet(Set<Area> area);
}
