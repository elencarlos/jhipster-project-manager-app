package br.gov.mec.polen.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import br.gov.mec.polen.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EfficiencyResultDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EfficiencyResultDTO.class);
        EfficiencyResultDTO efficiencyResultDTO1 = new EfficiencyResultDTO();
        efficiencyResultDTO1.setId(1L);
        EfficiencyResultDTO efficiencyResultDTO2 = new EfficiencyResultDTO();
        assertThat(efficiencyResultDTO1).isNotEqualTo(efficiencyResultDTO2);
        efficiencyResultDTO2.setId(efficiencyResultDTO1.getId());
        assertThat(efficiencyResultDTO1).isEqualTo(efficiencyResultDTO2);
        efficiencyResultDTO2.setId(2L);
        assertThat(efficiencyResultDTO1).isNotEqualTo(efficiencyResultDTO2);
        efficiencyResultDTO1.setId(null);
        assertThat(efficiencyResultDTO1).isNotEqualTo(efficiencyResultDTO2);
    }
}
