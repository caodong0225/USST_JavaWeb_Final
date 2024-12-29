package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.entity.Advertisement;
import usst.web.mapper.StatisticsMapper;
import usst.web.service.IStatisticsService;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-27
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;
    @Override
    public List<Advertisement> getTop10() {
        return statisticsMapper.getTop10();
    }
}
