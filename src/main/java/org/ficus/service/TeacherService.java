package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Teacher;
import org.ficus.data.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher findTeacherByUserId(Long id) {
        return teacherRepository.findTeacherByUser_Id(id);
    }

    

}
