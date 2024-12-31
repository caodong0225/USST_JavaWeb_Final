package usst.web.service;

import usst.web.entity.Advertisement;
import usst.web.pojo.DailyVisited;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
public interface IStatisticsService {
    List<Advertisement> getTop10();
    Integer getTotalNum();
    Integer getTotalVisit();
    List<DailyVisited> getRecentVisited();
}
