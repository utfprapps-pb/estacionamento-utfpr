package utfpr.edu.br.estacionamentoutfpr.security.social;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import utfpr.edu.br.estacionamentoutfpr.model.AuthProvider;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;
import utfpr.edu.br.estacionamentoutfpr.model.User;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.UserRepository;
import utfpr.edu.br.estacionamentoutfpr.security.AuthUserService;
import utfpr.edu.br.estacionamentoutfpr.security.SecurityConstants;
import utfpr.edu.br.estacionamentoutfpr.security.dto.AuthenticationResponse;
import utfpr.edu.br.estacionamentoutfpr.security.dto.UserResponseDTO;
import utfpr.edu.br.estacionamentoutfpr.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final GoogleTokenVerifier googleTokenVerifier;
    private final AuthUserService authUserService;
    private final UserService userService;
    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public AuthController(GoogleTokenVerifier googleTokenVerifier, AuthUserService authUserService,
                          UserService userService,
                          UserRepository userRepository,
                          AuthorityRepository authorityRepository) {
        this.googleTokenVerifier = googleTokenVerifier;
        this.authUserService = authUserService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @PostMapping
    ResponseEntity<AuthenticationResponse> auth(HttpServletRequest request, HttpServletResponse response) {
        String idToken = request.getHeader("Auth-Id-Token");
        if (idToken != null) {
            final Payload payload;
            try {
                payload = googleTokenVerifier.verify(idToken.replace(SecurityConstants.TOKEN_PREFIX, ""));
                if (payload != null) {
                    String username = payload.getEmail();
                    User user = userRepository.findByUsername(username);
                    if (user == null) {
                        user = new User();
                        user.setUsername(payload.getEmail());
                        user.setDisplayName( (String) payload.get("name"));
                        user.setPassword("P4ssword");
                        user.setProvider(AuthProvider.google);
                        user.setUserAuthorities(new HashSet<>());
                        user.getUserAuthorities().add(authorityRepository.findById(1L).orElse(new Authority()));
                        userService.save(user);
                    }

                    String token = JWT.create()
                            .withSubject(username)
                            .withExpiresAt(new Date(System.currentTimeMillis() +
                                    SecurityConstants.EXPIRATION_TIME))
                            .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

                    return  ResponseEntity.ok( new AuthenticationResponse(token, new UserResponseDTO(user)) );

                }
            } catch (Exception e) {
                // This is not a valid token, the application will send HTTP 401 as a response
            }
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}