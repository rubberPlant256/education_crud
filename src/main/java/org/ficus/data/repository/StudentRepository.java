package org.ficus.data.repository;

import org.ficus.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

     List<Student> findByParentId(Long parentId);
}
