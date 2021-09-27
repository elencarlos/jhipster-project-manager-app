package br.gov.mec.polen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Jpolen.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {}
