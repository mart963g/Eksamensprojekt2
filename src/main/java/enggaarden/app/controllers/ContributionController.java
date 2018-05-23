package enggaarden.app.controllers;

import enggaarden.app.models.Entities.ActivityType;
import enggaarden.app.models.Entities.Contribution;
import enggaarden.app.models.Factories.ContributionFactory;
import enggaarden.app.models.interfaces.ContributionRepositoryInterface;
import enggaarden.app.models.repositories.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContributionController
{
    /*
    Fields
     */
    @Autowired
    private ContributionRepositoryInterface contributionRepository = new ContributionRepository();
    private ContributionFactory contributionFactory = new ContributionFactory();

    /*
    Overview Methods
     */
    @GetMapping("/contributions")
    public String overview(Model model)
    {
        model.addAttribute("contributions", contributionFactory.getContributions(contributionRepository.getContributions()));
        return "/Contributions/contribution_overview";
    }

    /*
    Create Methods
     */
    @GetMapping("/contributions/create")
    public String create(Model model)
    {
        model.addAttribute("contribution", contributionFactory.createContribution());
        model.addAttribute("activities", ActivityType.values());
        return "/Contributions/contribution_create";
    }

    @PostMapping("/contributions/create")
    public String create(@ModelAttribute Contribution contribution)
    {
        contributionRepository.postContribution(contribution);
        return "redirect:/contributions";
    }

    /*
    Delete Methods
     */
    @GetMapping("/contribution/delete")
    public String delete(@RequestParam("contributionId") int id, Model model)
    {
        model.addAttribute("cont", contributionFactory.getContribution(contributionRepository.getContribution(id)));
        return "/Contributions/contribution_delete";
    }

    @PostMapping("/contribution/delete")
    public String delete(@RequestParam("contributionId") int id)
    {
        contributionRepository.deleteContribution(id);
        return "redirect:/contributions";
    }

}
