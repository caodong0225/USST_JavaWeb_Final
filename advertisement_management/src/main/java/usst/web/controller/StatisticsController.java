package usst.web.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import usst.web.entity.Advertisement;
import usst.web.service.IStatisticsService;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
@RequestMapping("/home")
@Controller
public class StatisticsController {
    @Resource
    IStatisticsService statisticsService;
    @GetMapping("/index")
    public String index(Model model) {
        List<Advertisement> top10 = statisticsService.getTop10();
        List<String> labels = top10.stream().map(Advertisement::getAdName).toList();
        List<Integer> visited = top10.stream().map(Advertisement::getVisitCount).toList();
        model.addAttribute("labels", labels);
        model.addAttribute("visited", visited);
        model.addAttribute("totalNum", statisticsService.getTotalNum());
        model.addAttribute("totalVisit", statisticsService.getTotalVisit());
        model.addAttribute("recentVisited", statisticsService.getRecentVisited());
        return "index";
    }
}
