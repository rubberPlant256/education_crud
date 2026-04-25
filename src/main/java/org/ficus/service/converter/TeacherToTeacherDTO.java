package org.ficus.service.converter;

import org.ficus.data.entity.Teacher;
import org.ficus.dto.TeacherDTO;

public class TeacherToTeacherDTO {
    public static TeacherDTO convertTeacherToTeacherDTO(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setMiddleName(teacher.getMiddleName());
//        teacherDTO.setPhone(teacher.getPhone());
//
//        teacherDTO.setTeachersCourses(teacher.getCourses());
        return teacherDTO;
    }
}
