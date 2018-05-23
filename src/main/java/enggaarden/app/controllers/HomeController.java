package enggaarden.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{

    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    @GetMapping("/")
    public String home2()
    {
        return "home";
    }

    @GetMapping("/fail")
    public String fail()
    {
        return "/fail";
    }

}
