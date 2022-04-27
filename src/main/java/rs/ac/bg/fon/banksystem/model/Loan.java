package rs.ac.bg.fon.banksystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial_num")
    private Long serialNum;
    private double amount;
    @Column(name = "date_of_approval")
    private LocalDate dateOfApproval;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "rate_amount")
    private double rateAmount;
    @Column(name = "rate_number")
    private int rateNumber;
    private double debt;
    @Transient
    private OperationStatus operation;
    private Long cb_report_id;

}
