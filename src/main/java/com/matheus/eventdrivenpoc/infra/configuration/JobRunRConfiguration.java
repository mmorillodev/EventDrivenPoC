package com.matheus.eventdrivenpoc.infra.configuration;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunRConfiguration {

    @Bean
    public StorageProvider getStorageProvider() {
        StorageProvider provider = new InMemoryStorageProvider();

        provider.setJobMapper(new JobMapper(new JacksonJsonMapper()));

        return provider;
    }
}
