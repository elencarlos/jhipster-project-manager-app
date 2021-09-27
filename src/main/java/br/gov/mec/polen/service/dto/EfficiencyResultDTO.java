package br.gov.mec.polen.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.gov.mec.polen.domain.EfficiencyResult} entity.
 */
public class EfficiencyResultDTO implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EfficiencyResultDTO)) {
            return false;
        }

        EfficiencyResultDTO efficiencyResultDTO = (EfficiencyResultDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, efficiencyResultDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EfficiencyResultDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
