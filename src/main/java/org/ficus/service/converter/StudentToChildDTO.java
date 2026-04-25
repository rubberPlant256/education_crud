package org.ficus.service.converter;

import org.ficus.data.entity.Groups;
import org.ficus.data.entity.Student;
import org.ficus.data.entity.Teacher;
import org.ficus.dto.ChildDTO;
import org.ficus.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentToChildDTO {

    public static ChildDTO convertStudentToChildDTO(Student student){
        ChildDTO childDTO = new ChildDTO();
        childDTO.setId(student.getId());
        childDTO.setLastName(student.getLastName());
        childDTO.setFirstName(student.getFirstName());
        childDTO.setMiddleName(student.getMiddleName());
        childDTO.setBirthDate(student.getBirthDate());

        List<String> groupCourse = new ArrayList<>();
        List<Groups> groupsList = student.getGroups();
        for (Groups groups : groupsList){
            String groupCourseName = groups.getId().toString() + " " + groups.getCourse().getName();
            groupCourse.add(groupCourseName);
        }

        childDTO.setChildGroups(groupCourse);

        return childDTO;
    }
}
