package br.com.projeto.LDS.config.security;

import br.com.projeto.LDS.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDetailSecurity implements UserDetails {

    private String email;
    private String password;
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailSecurity(String email, String password, Long id, Set<PerfilEnum> authorities) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.authorities = authorities.stream()
                .map(perfilEnum ->new SimpleGrantedAuthority(perfilEnum.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
