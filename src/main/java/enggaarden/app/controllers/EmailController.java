package enggaarden.app.controllers;

import enggaarden.app.models.interfaces.EmailRepositoryInterface;
import enggaarden.app.models.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController
{
    /*
    Fields
     */
    @Autowired
    private EmailRepositoryInterface er = new EmailRepository();

    /*
    String Generator Method
     */
    @GetMapping("/emails")
    public String getEmails(Model model)
    {
        model.addAttribute("primary", er.emailForPrimary());
        model.addAttribute("secondary", er.emailForSecondary());
        model.addAttribute("external", er.emailForExternal());
        model.addAttribute("board", er.emailForBoard());
        model.addAttribute("all", er.emailForAll());
        return "/Emails/email_send";
    }
}
