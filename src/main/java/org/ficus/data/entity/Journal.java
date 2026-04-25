package org.ficus.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Journal.updateJournalEntry",
                procedureName = "update_journal_entries",
                parameters = {
                        @StoredProcedureParameter(name = "p_schedule_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_student_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_attendance", type = Boolean.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_grade", type = String.class, mode = ParameterMode.IN)
                }
        )
})



@Entity
@Table(name = "journal")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(value = EnumType.STRING)
    private Score score;

    @NotNull
    private Date  lessonDate;

    private boolean attendance;
}
