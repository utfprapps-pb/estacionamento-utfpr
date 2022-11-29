package utfpr.edu.br.estacionamentoutfpr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
public class Operator implements UserDetails {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "O usuário não pode ser nulo")
    @Size(min = 4, max = 255, message = "O tamanho deve ser entre {min} e {max}")
    @UniqueUsername
    private String username;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 6, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 4, max = 255)
    private String name;

    @NotNull(message = "O sobrenome não pode ser nulo")
    @Size(min = 4, max = 255)
    private String surename;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String complement;

    @Size(max = 9)
    private String zipcode;

    @Size(max = 255)
    private String neighborhood;

    @Size(max = 255)
    private String city;

    private String documentFileName;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(
                    name = "operator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> userAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(this.userAuthorities);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
