package utfpr.edu.br.estacionamentoutfpr.validator;

import utfpr.edu.br.estacionamentoutfpr.annotation.UniqueUsername;
import utfpr.edu.br.estacionamentoutfpr.model.Operator;
import utfpr.edu.br.estacionamentoutfpr.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import utfpr.edu.br.estacionamentoutfpr.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator
        implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    OperatorRepository operatorRepository;

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (operatorRepository.findByUsername(username) == null) {
            return true;
        }
        return false;
    }
}
