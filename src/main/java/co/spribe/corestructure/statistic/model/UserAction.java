package co.spribe.corestructure.statistic.model;

import co.spribe.corestructure.config.Schemas;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "user_action")
@Table(name = "user_action", schema = Schemas.STATISTIC)
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

}
