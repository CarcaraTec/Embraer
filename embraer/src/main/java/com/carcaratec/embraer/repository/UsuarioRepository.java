package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    @Query("SELECT u from User u JOIN FETCH u.roles where username = :username ")
    User findByUsernameFetchRoles(@Param("username") String username);

    List<User> findByRolesId(UUID roleId);

}
