package org.ficus.data.repository;

import org.ficus.data.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    @Query("SELECT DISTINCT g FROM Groups g " +
            "JOIN Schedule s ON g.id = s.groups.id " +
            "WHERE s.teacher.id = :teacherId")
    List<Groups> findGroupsByTeacherId(@Param("teacherId") Long teacherId);

    Groups findGroupsById(Long id);


}
