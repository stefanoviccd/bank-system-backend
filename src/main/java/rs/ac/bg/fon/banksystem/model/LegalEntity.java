package rs.ac.bg.fon.banksystem.model;

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
@Table( name = "legal_entity",
        uniqueConstraints ={
                @UniqueConstraint(
                        name="identificationNumberConstraint",
                        columnNames = "identification_number"
                ),
                @UniqueConstraint(
                        name="accountConstraint",
                        columnNames = "account_number"
                )
        }

)


public class LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identification_number")
    @NotNull
    private String identificationNumber;
    @NotNull
    private String name;
    @Column(name = "account_number")
    @NotNull
    private String accountNumber;
   @OneToOne(
           cascade = {CascadeType.MERGE, CascadeType.PERSIST},
           optional = false,
            fetch = FetchType.EAGER)
   @JoinColumn( //po kojoj koloni join-ujemo
           name = "street_id", //kako ce se zvati u bazi
           referencedColumnName = "id"
           //koju kolonu referencira

   )
    private Street street;


}
