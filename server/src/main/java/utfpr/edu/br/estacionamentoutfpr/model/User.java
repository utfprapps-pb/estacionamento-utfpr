//package utfpr.edu.br.estacionamentoutfpr.model;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import java.util.*;
//
//@Data
//@Entity
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @NotNull
//    @UniqueUsername
//    private String username;
//
//    @NotNull
//    private String displayName;
//
//    @NotNull
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private AuthProvider provider;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "users_authorities",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "authority_id", referencedColumnName = "id"))
//    private Set<Authority> userAuthorities;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.addAll(this.userAuthorities);
//        return authorities;
//    }
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
