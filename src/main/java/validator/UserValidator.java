package validator;

import model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();

        ValidationUtils.rejectIfEmpty(errors,"phoneNumber", "phoneNumber_empty");
        ValidationUtils.rejectIfEmpty(errors,"email", "email_empty");

        if (!phoneNumber.startsWith("+84")){
            errors.rejectValue("phoneNumber", "phoneNumber_startInvalid");
        }

        if (phoneNumber.length()>12 || phoneNumber.length() == 0){
            errors.rejectValue("phoneNumber","phoneNumber_length");
        }

        if (!email.matches("\\w+@\\w+.\\w+")){
            errors.rejectValue("email","email_invalid");
        }
    }
}
