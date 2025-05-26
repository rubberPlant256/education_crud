package org.ficus.controller.parent;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ficus.auth.cookie.SessionCookieProvider;
import org.ficus.auth.services.AuthService;
import org.ficus.auth.services.CustomResponse;
import org.ficus.data.entity.Role;
import org.ficus.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parent")
public class AuthParentController {

    private final AuthService authService;

    //показ формы входа студента
    @GetMapping("/sign-in")
    public String showSignInForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "parent_login";
    }

    //обработка данных формы входа студента
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("userDTO") UserDTO userDTO, HttpServletResponse response){
        CustomResponse authResponse = authService.signIn(userDTO);
        SessionCookieProvider.setUpStudentSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/parent/main";
    }

    //показ формы регистрации
    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "parent_registration";
    }

    //обработка формы регистрации
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("userDTO") UserDTO userDTO){
        authService.signUp(userDTO, Role.PARENT);
        return "redirect:/parent/sign-in";
    }
}
