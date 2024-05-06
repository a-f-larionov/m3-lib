package m3.lib.repositories;

import m3.lib.BaseDataJpaTest;
import m3.lib.entities.UserAgentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "logging.level.org.springframework.jdbc.core=DEBUG",
        "logging.level.org.hibernate.SQL=DEBUG"
})
@Commit
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAgentRepositoryTest extends BaseDataJpaTest {

    @Autowired
    private UserAgentRepository userAgentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testSaveOne() {
        // given
        final var uid = 123L;
        final var agent = "user-agent-string2";

        //@todo-b replace all sql to lower case
        jdbcTemplate.update("delete from user_agents");
        final var entity = UserAgentEntity.builder()
                .uid(uid)
                .agent(agent)
                .build();
        // when
        userAgentRepository.save(entity);

        // then
        final var data = jdbcTemplate.queryForMap("SELECT * FROM user_agents");
        assertEquals(uid, data.get("uid"));
        assertEquals(agent, data.get("agent"));
    }
}
