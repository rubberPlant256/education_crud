package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Student;
import org.ficus.data.repository.StudentRepository;
import org.ficus.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findChildrenByParentId(Long parentId){

        return studentRepository.findByParentId(parentId);
    }

    public void createStudentByStudentDTO(StudentDTO studentDTO){
//        LocalDate localDate = LocalDate.parse(studentDTO.getBirthDate());
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate localDate = LocalDate.parse(studentDTO.getBirthDate());
        // Используем java.sql.Date вместо java.util.Date
        Date date = java.sql.Date.valueOf(localDate);

        studentRepository.addStudent(studentDTO.getLastName(),
               studentDTO.getFirstName(),
               studentDTO.getMiddleName(),
               date,
               studentDTO.getParentId());
    }


}
