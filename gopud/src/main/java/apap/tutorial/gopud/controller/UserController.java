package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user){
        userService.addUser(user);
        return "home";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    private String changePasswordFormPage(){
        return "form-change-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String changePasswordSubmitPage(@ModelAttribute PasswordModel passwords, Model model){
        String password = passwords.getPassword();
        String newPassword = passwords.getNewPassword();
        String confirmPassword = passwords.getConfirmPassword();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = userService.findByUsername(username);

        //Check password
        if(userService.isMatch(password, user.getPassword())){
            if(newPassword.equals(confirmPassword)){
                user.setPassword(newPassword);
                userService.changePassword(user);
                model.addAttribute("success", "Password has changed");
            }
        }else {
            model.addAttribute("pesan", "Password doesn't match");
        }
        return "form-change-password";
    }

}

class PasswordModel {
    private String password;
    private String newPassword;
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}