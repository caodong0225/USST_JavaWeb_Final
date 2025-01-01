package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import usst.web.entity.Advertisement;
import usst.web.mapper.TagMapper;
import usst.web.service.ITagService;

import java.util.List;

/**
 * @author jyzxc
 * @since 2025-1-1
 */
@Service
public class TagServiceImpl implements ITagService {
    @Resource
    TagMapper tagMapper;
    @Override
    public Integer getRecommendationUri() {
        List<Advertisement> advertisements = tagMapper.getRecommendation();
        // 抽取随机广告
        return advertisements.get(0).getAdId();
    }
}
