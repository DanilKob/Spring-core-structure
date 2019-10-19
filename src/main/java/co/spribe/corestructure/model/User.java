package co.spribe.corestructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(schema = Schemas.PINGS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;


    @Column(name = "login", unique = true)
    private String login;

    @OneToMany(mappedBy = "user")
    private List<Ping> pings;
}
