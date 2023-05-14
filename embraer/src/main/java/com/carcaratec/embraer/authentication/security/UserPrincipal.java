package com.carcaratec.embraer.authentication.security;

import com.carcaratec.embraer.model.dto.Usuario;
import com.carcaratec.embraer.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    @Autowired
    PermissaoRepository permissaoRepository;

    private String nome;

    private String senha;

    private Collection<? extends GrantedAuthority> permissao;

    public UserPrincipal (Usuario usuario){
        this.nome = usuario.getNome();
        this.senha = usuario.getSenha();
        List<SimpleGrantedAuthority> permissoes = new ArrayList<>();

        permissoes = usuario.getRoles().stream().map(role ->{
            return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
        }).collect(Collectors.toList());
        this.permissao = permissoes;
    }

    public static UserPrincipal create(Usuario usuario){
        return new UserPrincipal(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissao;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
