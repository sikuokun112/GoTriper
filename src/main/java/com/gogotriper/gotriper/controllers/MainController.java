package com.gogotriper.gotriper.controllers;

import com.gogotriper.gotriper.entity.Account;
import com.gogotriper.gotriper.services.UserService;
import com.gogotriper.gotriper.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/test"},method =  RequestMethod.GET)
    public String get(){
        return "success";
    }

    // main
    @RequestMapping(value = {"/home"},method =  RequestMethod.GET)
    public String HomeMain(){
        return "homemain";
    }

    @RequestMapping(value = {"/detail"},method =  RequestMethod.GET)
    public String DetailMain(){
        return "detailmain";
    }
    @RequestMapping(value = {"/listing"},method =  RequestMethod.GET)
    public String Listing(){
        return "listingmain";
    }
    //main


    @RequestMapping(value = {"/signup"},method =  RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("user", new Account());
        return "registerForm";
    }

    @RequestMapping(value = {"/signup2"},method =  RequestMethod.GET)
    public String registerForm2(Model model) {

        model.addAttribute("user", new Account());
        return "signup";
    }



    @PostMapping("/register")
    public String registerUser(@Valid  Account account, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registerForm2";
        }
        if(userService.isUserPresent(account.getUserName())) {
            model.addAttribute("exist",true);
            return "redirect:/signup2";
        }else{
            userService.createUser(account);
            return "success";
        }


    }

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage2(Model model) {

        return "login";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
}
