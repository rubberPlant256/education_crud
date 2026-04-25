package org.ficus.data.repository;

import org.ficus.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

     List<Student> findByParentId(Long parentId);

     @Procedure(procedureName = "add_student")
     void addStudent(
             @Param("p_last_name") String last_name,
             @Param("p_first_name") String first_name,
             @Param("p_middle_name") String middle_name,
             @Param("p_birth_date") Date birth_date,
             @Param("p_parent_id") Long parent_id
     );
}
