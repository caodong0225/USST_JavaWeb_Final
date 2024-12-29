package usst.web.service;

import usst.web.entity.Advertisement;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
public interface IStatisticsService {
    List<Advertisement> getTop10();
}
