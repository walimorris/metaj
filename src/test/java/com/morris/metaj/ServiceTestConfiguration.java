package com.morris.metaj;

import com.morris.metaj.service.impl.*;
import org.mockito.Mockito;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ServiceTestConfiguration {

    @Bean
    @Primary
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

    @Bean
    @Primary
    public CloudWatchServiceImpl cloudWatchService() {
        return Mockito.mock(CloudWatchServiceImpl.class);
    }

    @Bean
    @Primary
    public MetaJS3ServiceImpl metaJS3Service() {
        return Mockito.mock(MetaJS3ServiceImpl.class);
    }

    @Bean
    @Primary
    public AwsClientsBuilderImpl awsClientsBuilder() {
        return Mockito.mock(AwsClientsBuilderImpl.class);
    }

    @Bean
    @Primary
    public CommandExecutorImpl commandExecutor() {
        return Mockito.mock(CommandExecutorImpl.class);
    }

    @Bean
    @Primary
    public InstanceCommandsImpl instanceCommands() {
        return Mockito.mock(InstanceCommandsImpl.class);
    }

    @Bean
    @Primary
    public InstanceInitializerImpl instanceInitializer() {
        return Mockito.mock(InstanceInitializerImpl.class);
    }

    @Bean
    @Primary
    public InstanceMappingsImpl instanceMappings() {
        return Mockito.mock(InstanceMappingsImpl.class);
    }

    @Bean
    @Primary
    public MetaJAwsIamServiceImpl metaJAwsIamService() {
        return Mockito.mock(MetaJAwsIamServiceImpl.class);
    }

    @Bean
    @Primary
    public MetaJSetupServiceImpl metaJSetupService() {
        return Mockito.mock(MetaJSetupServiceImpl.class);
    }
}
