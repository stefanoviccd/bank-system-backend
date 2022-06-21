package rs.ac.bg.fon.banksystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Street.findAll", query = "SELECT s FROM Street s"),
        @NamedQuery(name = "Street.findByStreetName", query = "SELECT s FROM Street s WHERE s.streetName = :streetName"),
})
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_number")
    private int streetNumber;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            optional = false,
            fetch = FetchType.EAGER

            )

    @JoinColumn( //po kojoj koloni join-ujemo
            name = "zip_code", //kako ce se zvati u bazi
            referencedColumnName = "zip_code",
            unique = false //koju kolonu referencira

    )

    private Township township;


    public void updateStreet(Street street) {
        this.streetName=street.getStreetName();
        this.streetNumber=street.getStreetNumber();
    }
}
