package org.ficus.data.repository;

import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findParentByUser_Id(Long id);

    @Query(value = "SELECT update_parent(:p_id, :p_last_name, :p_first_name, :p_middle_name, :p_phone)", nativeQuery = true)
    void updateParent(@Param("p_id") Long parentId,
                            @Param("p_last_name") String lastName,
                            @Param("p_first_name") String firstName,
                            @Param("p_middle_name") String middleName,
                            @Param("p_phone") String phone);

}
