package org.ficus.service.converter;

import org.ficus.data.entity.*;
import org.ficus.dto.ScheduleDTO;
import org.ficus.dto.TeacherDTO;

public class ScheduleToScheduleDTO {

    public static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setTeacher(schedule.getTeacher());
        scheduleDTO.setGroup(schedule.getGroups());
        scheduleDTO.setDay(schedule.getLesson().getDay());
        scheduleDTO.setTime(schedule.getLesson().getTime());

        return scheduleDTO;
    }
}
