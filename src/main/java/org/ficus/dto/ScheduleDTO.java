package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ficus.data.entity.Day;
import org.ficus.data.entity.Groups;
import org.ficus.data.entity.Teacher;
import org.ficus.data.entity.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long id;

    private Teacher teacher;

    private Groups group;

    private Day day;

    private Time time;

}
