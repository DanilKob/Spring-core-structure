package co.spribe.corestructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private long id;


    @Column(name = "login", unique = true)
    private String login;

    @OneToMany(mappedBy = "user")
    private List<Ping> pings;
}
