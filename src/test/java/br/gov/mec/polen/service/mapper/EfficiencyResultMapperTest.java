package br.gov.mec.polen.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EfficiencyResultMapperTest {

    private EfficiencyResultMapper efficiencyResultMapper;

    @BeforeEach
    public void setUp() {
        efficiencyResultMapper = new EfficiencyResultMapperImpl();
    }
}
