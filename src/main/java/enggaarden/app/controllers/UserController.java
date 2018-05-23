package enggaarden.app.controllers;

import enggaarden.app.models.Factories.UserFactory;
import enggaarden.app.models.interfaces.UserRepositoryInterface;
import enggaarden.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import enggaarden.app.models.Entities.*;

@Controller
public class UserController
{
    /*
    Fields
     */
    @Autowired
    private UserRepositoryInterface userRepository = new UserRepository();
    private UserFactory userFactory = new UserFactory();


    /*
    Overview Method
     */
    @GetMapping("/users")
    public String users(Model model)
    {
        model.addAttribute("users",userFactory.getUsers(userRepository.get()));
        return "/Users/users_overview";
    }


    /*
    Create methods
     */
    @GetMapping("/users/create")
    public String create(Model model)
    {
        model.addAttribute("usr", userFactory.createUser());
        model.addAttribute("userTypes", userRepository.getUserTypes());
        return "/Users/users_create";
    }

    @PostMapping("/users/create")
    public String create(@ModelAttribute User user)
    {
        userRepository.postUser(user);
        return "redirect:/users";
    }


    /*
    Delete Methods
     */
    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam("username") String username, Model model)
    {
        model.addAttribute("usr", userFactory.getUser(userRepository.get(username)));
        return "/Users/user_delete";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("username") String username)
    {
        userRepository.delete(username);
        return "redirect:/users";
    }

    /*
    Login Method
     */
    @GetMapping("/login")
    public String login()
    {
        return "/login";
    }

}
