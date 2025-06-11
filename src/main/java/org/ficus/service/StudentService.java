package org.ficus.service;

import lombok.RequiredArgsConstructor;
import org.ficus.data.entity.Student;
import org.ficus.data.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findChildrenByParentId(Long parentId){

        return studentRepository.findByParentId(parentId);
    }
}
