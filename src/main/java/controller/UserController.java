package controller;

import model.User;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import validator.UserValidator;

@Controller
public class UserController {

    @GetMapping("/user")
    ModelAndView showForm(){
        return new ModelAndView("index","user",new User());
    }

    @PostMapping("/validate")
    public ModelAndView validate(@Validated @ModelAttribute User user, BindingResult bindingResult){
        new UserValidator().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("index");
        }
        return new ModelAndView("ok");
    }
}
