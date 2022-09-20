package utfpr.edu.br.estacionamentoutfpr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Operator {
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

    private Profile profile;
}
