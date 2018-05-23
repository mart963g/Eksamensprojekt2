package enggaarden.app.controllers;

import enggaarden.app.models.Factories.SubLogFactory;
import enggaarden.app.models.interfaces.SubLogRepositoryInterface;
import enggaarden.app.models.repositories.SubLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubLogController
{
    /*
    Fields
     */
    @Autowired
    private SubLogRepositoryInterface subLogRepository = new SubLogRepository();
    private SubLogFactory subLogFactory = new SubLogFactory();

    /*
    Overview Method
     */
    @GetMapping("/subLogs")
    public String subLogOverview(Model model)
    {
        model.addAttribute("subLogs", subLogFactory.getSubLogs(subLogRepository.getSubLogs()));
        return "/SubLogs/subLog_overview";
    }

    /*
    Delete Methods
     */
    @GetMapping("/clearLog")
    public String clearLog()
    {
        return "/Sublogs/subLog_clear";
    }

    @PostMapping("/clearLog")
    public String deleteLog()
    {
        subLogRepository.clearLog();
        return "redirect:/subLogs";
    }
}
