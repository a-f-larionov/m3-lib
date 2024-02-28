package m3.lib.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "socnetuserid")
    @NotNull
    private Long socNetUserId;

    @Column(name = "socnettypeid")
    @NotNull
    private Long socNetTypeId;

    @Column(name = "create_tm")
    @NotNull
    private Long createTm;

    @Column(name = "login_tm")
    private Long loginTm;

    @Column(name = "logout_tm")
    private Long logoutTm;

    @Column(name = "nextpointid")
    @NotNull
    private Long nextPointId;

    @Column(name = "fullrecoverytime")
    @NotNull
    private Long fullRecoveryTime;
}

