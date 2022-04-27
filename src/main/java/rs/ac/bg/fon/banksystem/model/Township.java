package rs.ac.bg.fon.banksystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(
                        name="zipCodeConstraint",
                        columnNames = "zip_code"
                )


)
@NamedQueries({
        @NamedQuery(name = "TownShip.findAll", query = "SELECT s FROM Township s"),
        @NamedQuery(name = "TownShip.findByZipCode", query = "SELECT s FROM Township s WHERE s.zipCode = :zipCode"),
})
public class Township implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zip_code")
    @NotNull
    private Long zipCode;
    @NotNull
    private String name;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            optional = false   )

    @JoinColumn( //po kojoj koloni join-ujemo
            name = "place_id", //kako ce se zvati u bazi
            referencedColumnName = "id" //koju kolonu referencira
    )
    private Place place;

     /*  @JsonIgnore
 @OneToMany(
            mappedBy = "township",
            orphanRemoval = true

    )
    private List<Street> streets;*/


}
