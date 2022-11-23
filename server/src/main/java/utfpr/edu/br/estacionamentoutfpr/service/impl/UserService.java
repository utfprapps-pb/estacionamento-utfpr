package utfpr.edu.br.estacionamentoutfpr.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utfpr.edu.br.estacionamentoutfpr.model.AuthProvider;
import utfpr.edu.br.estacionamentoutfpr.model.Authority;
import utfpr.edu.br.estacionamentoutfpr.model.User;
import utfpr.edu.br.estacionamentoutfpr.repository.AuthorityRepository;
import utfpr.edu.br.estacionamentoutfpr.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserService {

    UserRepository userRepository;

    AuthorityRepository authorityRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService (UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.authorityRepository = authorityRepository;
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProvider(AuthProvider.local);
        user.setUserAuthorities(new HashSet<>());
        user.getUserAuthorities().add(authorityRepository.findById(1L).orElse(new Authority()));
        return userRepository.save(user);
    }
}
