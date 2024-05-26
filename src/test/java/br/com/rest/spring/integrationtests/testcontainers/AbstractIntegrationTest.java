package br.com.rest.spring.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(inheritInitializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            System.setProperty("spring.datasource.url", "jdbc:tc:postgresql:13-alpine:///testdb");
            System.setProperty("spring.datasource.username", "testuser");
            System.setProperty("spring.datasource.password", "testpassword");
        }
}
