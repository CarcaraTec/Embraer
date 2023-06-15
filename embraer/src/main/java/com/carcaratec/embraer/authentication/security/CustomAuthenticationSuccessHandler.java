package com.carcaratec.embraer.authentication.security;

import com.carcaratec.embraer.model.session.Session;
import com.carcaratec.embraer.model.session.SessionRepository;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Session session = new Session();

        session.setUserId(usuarioRepository.findByUsername(username).getId());

        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();

        session.setLoginTime(new Timestamp(timestamp));

        sessionRepository.save(session);

        System.out.println("Usuário logado: " + username);

        // Redirecionar para a página desejada após o login bem-sucedido
        response.sendRedirect("http://localhost:3000"); // substitua "/dashboard" pela URL desejada
    }
}
