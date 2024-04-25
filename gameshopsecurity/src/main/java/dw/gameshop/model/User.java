package dw.gameshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="user")
public class User implements UserDetails { // UserDetails 를 상속받아 사용
    @Id
    @Column(name="user_id", length=100)
    private String userId;
    @Column(name="user_name", length=255, nullable = false)
    private String userName;
    @Column(name="email", length=255, nullable = false)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // boolean 계정이 만료되지 않았습니까? 정상 true

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // boolean 계정이 잠기지 않았습니까? 정상 true

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // boolean 자격 증명이 만료되지 않았습니까? 정상 true

    @Override
    public boolean isEnabled() {
        return true;
    } // boolean 정상 true
}
