package org.ficus.data.repository;

import org.ficus.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByUser_Id(Long id);
}

