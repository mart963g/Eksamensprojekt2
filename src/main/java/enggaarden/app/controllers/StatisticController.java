package enggaarden.app.controllers;

import enggaarden.app.models.Factories.StatisticFactory;
import enggaarden.app.models.interfaces.StatisticRepositoryInterface;
import enggaarden.app.models.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController
{
    /*
    Fields
     */
    @Autowired
    private StatisticRepositoryInterface statisticRepository = new StatisticRepository();
    private StatisticFactory statisticFactory = new StatisticFactory();

    /*
    Overview Method
     */
    @GetMapping("/statistics")
    public String statisticsOverview(Model model)
    {
        model.addAttribute("sum", statisticRepository.getSum());
        model.addAttribute("statistic", statisticFactory.statistics(statisticRepository.get()));
        return "/Statistics/statistics_overview";
    }
}