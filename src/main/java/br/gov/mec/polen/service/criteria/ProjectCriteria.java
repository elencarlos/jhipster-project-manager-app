package br.gov.mec.polen.service.criteria;

import br.gov.mec.polen.domain.enumeration.ProjectStatus;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link br.gov.mec.polen.domain.Project} entity. This class is used
 * in {@link br.gov.mec.polen.web.rest.ProjectResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /projects?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProjectCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ProjectStatus
     */
    public static class ProjectStatusFilter extends Filter<ProjectStatus> {

        public ProjectStatusFilter() {}

        public ProjectStatusFilter(ProjectStatusFilter filter) {
            super(filter);
        }

        @Override
        public ProjectStatusFilter copy() {
            return new ProjectStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private ProjectStatusFilter status;

    private LongFilter areaId;

    private Boolean distinct;

    public ProjectCriteria() {}

    public ProjectCriteria(ProjectCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.areaId = other.areaId == null ? null : other.areaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProjectCriteria copy() {
        return new ProjectCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public ProjectStatusFilter getStatus() {
        return status;
    }

    public ProjectStatusFilter status() {
        if (status == null) {
            status = new ProjectStatusFilter();
        }
        return status;
    }

    public void setStatus(ProjectStatusFilter status) {
        this.status = status;
    }

    public LongFilter getAreaId() {
        return areaId;
    }

    public LongFilter areaId() {
        if (areaId == null) {
            areaId = new LongFilter();
        }
        return areaId;
    }

    public void setAreaId(LongFilter areaId) {
        this.areaId = areaId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProjectCriteria that = (ProjectCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(status, that.status) &&
            Objects.equals(areaId, that.areaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status, areaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (areaId != null ? "areaId=" + areaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
