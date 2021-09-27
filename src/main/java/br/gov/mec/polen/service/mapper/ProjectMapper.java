package br.gov.mec.polen.service.mapper;

import br.gov.mec.polen.domain.*;
import br.gov.mec.polen.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = { AreaMapper.class })
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    @Mapping(target = "areas", source = "areas", qualifiedByName = "nameSet")
    ProjectDTO toDto(Project s);

    @Mapping(target = "removeArea", ignore = true)
    Project toEntity(ProjectDTO projectDTO);
}
