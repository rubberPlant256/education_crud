package org.ficus.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ficus.data.entity.Schedule;
import org.ficus.data.entity.Score;
import org.ficus.data.entity.Student;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDTO {

    private Long id;

    private Schedule schedule;

    private Student student;

    private Score score;

    private Date lessonDate;

    private boolean attendance;
}
