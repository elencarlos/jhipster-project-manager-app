package br.gov.mec.polen.domain;

import static org.assertj.core.api.Assertions.assertThat;

import br.gov.mec.polen.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EfficiencyResultTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EfficiencyResult.class);
        EfficiencyResult efficiencyResult1 = new EfficiencyResult();
        efficiencyResult1.setId(1L);
        EfficiencyResult efficiencyResult2 = new EfficiencyResult();
        efficiencyResult2.setId(efficiencyResult1.getId());
        assertThat(efficiencyResult1).isEqualTo(efficiencyResult2);
        efficiencyResult2.setId(2L);
        assertThat(efficiencyResult1).isNotEqualTo(efficiencyResult2);
        efficiencyResult1.setId(null);
        assertThat(efficiencyResult1).isNotEqualTo(efficiencyResult2);
    }
}
