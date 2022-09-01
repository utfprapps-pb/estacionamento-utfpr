package utfpr.edu.br.estacionamentoutfpr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "O usuário não pode ser nulo")
    @Size(min = 4, max = 255, message = "O tamanho de ser entre {min} e {max}")
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 6, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    @NotNull
    @Size(min = 4, max = 255)
    private String name;

    @NotNull
    @Size(min = 4, max = 255)
    private String surename;

    @NotNull
    private String email;

    private String address;

    private String complement;

    private String zipcode;

    private String neighborhood;

    private String city;

    private String documentFileName;

    private Profile profile;
}
