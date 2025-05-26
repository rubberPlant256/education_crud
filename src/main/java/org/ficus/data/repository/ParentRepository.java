package org.ficus.data.repository;

import org.ficus.data.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findParentByUser_Id(Long id);
}
