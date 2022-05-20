package rs.ac.bg.fon.banksystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_bureau_report",
        uniqueConstraints ={
                @UniqueConstraint(
                        name="identificationNumberConstraint",
                        columnNames = "report_num"
                )

        }
)
public class CreditBureauReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "report_num")
    private String reportNum;
    private LocalDate date;
    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "report_status")
    private ReportStatus reportStatus;
    @OneToMany(cascade = { CascadeType.MERGE},
    orphanRemoval = true,
    fetch = FetchType.EAGER)
    @JoinColumn(name = "cb_report_id",
            referencedColumnName ="id" )
    private List<Loan> loans;
    @ManyToOne
    @JoinColumn(name = "legal_entity_id",
    referencedColumnName = "id")
    private LegalEntity legalEntity;


}
