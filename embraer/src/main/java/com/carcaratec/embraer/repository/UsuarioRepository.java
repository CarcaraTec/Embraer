package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByLogin(String username);

//    @Query(value = "SELECT u.LOGIN, u.NOME, u.EMAIL, u.SENHA, r.ROLES_ID, r.ROLES_NAME\n" +
//            "FROM USUARIO u\n" +
//            "JOIN USUARIO_ROLES ur ON u.LOGIN = ur.LOGIN_USUARIO\n" +
//            "JOIN ROLES r ON ur.ID_ROLES = r.ROLES_ID\n" +
//            "WHERE u.LOGIN = :username")
//    Usuario findByLoginFetchRoles(@Param("username")String username);

}
