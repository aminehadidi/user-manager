package fr.app.usermanager;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
/**
 * class UserManagerConfigurationTests 
 * Configuration du postgreDBContainer pour les tests unit
 *
 * @author Amine HADIDI
 */
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = UserManagerConfigurationTests.DockerPostgreDataSourceInitializer.class)
public abstract class UserManagerConfigurationTests {

    private static final String IMAGE = "postgres";

    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>(IMAGE)
            .withDatabaseName("integration-tests-db").withUsername("sa").withPassword("sa");

    static {
        postgreDBContainer.start();
    }

    public static class DockerPostgreDataSourceInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword());
        }
    }

}
