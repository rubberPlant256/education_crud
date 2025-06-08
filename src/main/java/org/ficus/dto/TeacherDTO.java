package org.ficus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ficus.data.entity.Course;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;
}

//    private String phone;
//
//    private List<Course> teachersCourses;
//}
