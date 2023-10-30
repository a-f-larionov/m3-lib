package m3.lib.entities;

import jakarta.persistence.*;
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
    private Long socNetUserId;

    @Column(name = "socnettypeid")
    private Long socNetTypeId;

    @Column(name = "create_tm")
    private Long createTm;

    @Column(name = "login_tm")
    private Long loginTm;

    @Column(name = "logout_tm")
    private Long logoutTm;

    @Column(name = "nextpointid")
    private Long nextPointId;

    @Column(name = "fullrecoverytime")
    private Long fullRecoveryTime;
}

