package rs.ac.bg.fon.banksystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Place.findAll", query = "SELECT s FROM Place s"),
        @NamedQuery(name = "City.findByName", query = "SELECT s FROM Place s WHERE s.name = :name"),
})
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String name;
    /*
    @JsonIgnore
    @OneToMany(
            mappedBy = "place"
    )
    private List<Township> townships;*/


}
