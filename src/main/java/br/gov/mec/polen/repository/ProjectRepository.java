package br.gov.mec.polen.repository;

import br.gov.mec.polen.domain.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Project entity.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    @Query(
        value = "select distinct project from Project project left join fetch project.areas",
        countQuery = "select count(distinct project) from Project project"
    )
    Page<Project> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct project from Project project left join fetch project.areas")
    List<Project> findAllWithEagerRelationships();

    @Query("select project from Project project left join fetch project.areas where project.id =:id")
    Optional<Project> findOneWithEagerRelationships(@Param("id") Long id);
}
