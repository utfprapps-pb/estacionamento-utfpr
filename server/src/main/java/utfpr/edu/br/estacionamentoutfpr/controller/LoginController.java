package utfpr.edu.br.estacionamentoutfpr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.security.AuthUserService;
import utfpr.edu.br.estacionamentoutfpr.security.UserDTO;

import java.security.Principal;

@RestController
@RequestMapping("login")
public class LoginController {

    private final AuthUserService authUserService;

    public LoginController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("user-info")
    public UserDTO getUserInfo(Principal principal) {
        return new UserDTO( (Operator)
                authUserService.loadUserByUsername(principal.getName()));
    }
}
