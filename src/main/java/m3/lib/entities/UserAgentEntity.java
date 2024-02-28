package m3.lib.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_agents")
@Builder
@AllArgsConstructor
public class UserAgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long uid;

    @NotBlank
    private String agent;
}
