package usst.web.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import usst.web.entity.Advertisement;
import usst.web.pojo.DailyVisited;
import usst.web.service.IStatisticsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Integer> recentVisited;
        // 从数据库查询近十天的访问数据
        List<DailyVisited> dbData = statisticsService.getRecentVisited();

        // 获取近十天的日期列表
        List<String> lastTenDays = getLastTenDays();

        // 构造结果映射
        Map<String, Integer> dateToCountMap = dbData.stream()
                .collect(Collectors.toMap(
                        item -> item.getDate().toLocalDate().toString(), // 转换 LocalDateTime 为 yyyy-MM-dd
                        DailyVisited::getCount
                ));
        recentVisited = lastTenDays.stream()
                .map(date -> dateToCountMap.getOrDefault(date, 0)) // 没有数据填充为0
                .collect(Collectors.toList());
        model.addAttribute("labels", labels);
        model.addAttribute("visited", visited);
        model.addAttribute("totalNum", statisticsService.getTotalNum());
        model.addAttribute("totalVisit", statisticsService.getTotalVisit());
        model.addAttribute("date", lastTenDays);
        model.addAttribute("recentVisited", recentVisited);
        return "index";
    }

    private List<String> getLastTenDays() {
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 9; i >= 0; i--) {
            dates.add(today.minusDays(i).toString());
        }
        return dates;
    }
}
