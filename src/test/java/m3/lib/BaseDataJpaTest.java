package m3.lib;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@DataJpaTest
@ActiveProfiles("test")
public class BaseDataJpaTest {
    static final MySQLContainer<?> mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:5.7"))
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);
        mySQLContainer.addExposedPort(33306);
        mySQLContainer.start();
    }

    @DynamicPropertySource
    private static void DynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }
}
