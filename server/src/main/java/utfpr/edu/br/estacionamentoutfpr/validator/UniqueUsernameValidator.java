package utfpr.edu.br.estacionamentoutfpr.validator;

import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import utfpr.edu.br.estacionamentoutfpr.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator
        implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.findByUsername(username) == null) {
            return true;
        }
        return false;
    }
}
