package utfpr.edu.br.estacionamentoutfpr.security.social;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.edu.br.estacionamentoutfpr.model.AuthProvider;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import utfpr.edu.br.estacionamentoutfpr.security.AuthUserService;
import utfpr.edu.br.estacionamentoutfpr.security.SecurityConstants;
import utfpr.edu.br.estacionamentoutfpr.security.dto.AuthenticationResponse;
import utfpr.edu.br.estacionamentoutfpr.security.dto.UserResponseDTO;
import utfpr.edu.br.estacionamentoutfpr.service.
        OperatorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final GoogleTokenVerifier googleTokenVerifier;
    private final AuthUserService authUserService;
    private final OperatorService operatorService;
    private final OperatorRepository operatorRepository;

    private final AuthorityRepository authorityRepository;

    public AuthController(GoogleTokenVerifier googleTokenVerifier, AuthUserService authUserService,
                          OperatorService operatorService,
                          OperatorRepository operatorRepository,
                          AuthorityRepository authorityRepository) {
        this.googleTokenVerifier = googleTokenVerifier;
        this.authUserService = authUserService;
        this.operatorService = operatorService;
        this.operatorRepository = operatorRepository;
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
                    Operator operator = operatorRepository.findByUsername(username);
                    if (operator == null) {
                        operator = new Operator();
                        operator.setUsername(payload.getEmail());
                        operator.setName( (String) payload.get("name"));
                        operator.setPassword("P4ssword");
                        operator.setProvider(AuthProvider.google);
                        operator.setUserAuthorities(new HashSet<>());
                        operator.getUserAuthorities().add(authorityRepository.findById(1L).orElse(new Authority()));
                        operatorService.save(operator);
                    }

                    String token = JWT.create()
                            .withSubject(username)
                            .withExpiresAt(new Date(System.currentTimeMillis() +
                                    SecurityConstants.EXPIRATION_TIME))
                            .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

                    return  ResponseEntity.ok( new AuthenticationResponse(token, new UserResponseDTO(operator)) );

                }
            } catch (Exception e) {
                // This is not a valid token, the application will send HTTP 401 as a response
            }
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}