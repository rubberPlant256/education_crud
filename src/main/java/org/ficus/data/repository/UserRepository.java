package org.ficus.data.repository;

import org.ficus.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByLogin(String login);

    Users findUserById(long id);

}
